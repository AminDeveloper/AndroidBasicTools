package com.parax.tiro.Helper

import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.paraxco.commontools.Observers.NetworkObserverHandler
import com.paraxco.commontools.R
import com.paraxco.commontools.Utils.SmartLogger
import com.paraxco.commontools.Utils.Utils
import java.lang.Exception
import java.util.concurrent.atomic.AtomicBoolean

/**
 * calls specified method [call]  with specified [delaySecond] if network is available after calling retry()
 * disable it in on destroy  events
 *
 * if network become available retries automatically
 *
 */
class RetryHelper(val context: Context, var numOfFinished: Int = 1) : NetworkObserverHandler.NetworkChangeObserver {

    companion object {
        fun getInstanceAndCall(context: Context, call: () -> Any?, numOfFinished: Int = 1): RetryHelper {
            val instance = RetryHelper(context, numOfFinished)
            instance.initializeAndCall(call)
            return instance
        }

        private var dialog: Dialog? = null
        private var dismissed = false//in each timee network connects just show it once and if dialog dismissed by user do not show it again unless network is connected and disconnected again
        val networkErrorDialog=R.layout.network_error_dialog


    }

    var enabled = true
    var delaySecond: Long = 8
    var call: (() -> Any?)? = null
    fun initializeAndCall(call: () -> Any?) {
        this.call = call
        call.invoke()
    }

    fun retry() {
        retry(true)
    }

    private var calling = AtomicBoolean(false)
    /**
     * call it when something has gone wrong and retry needed
     */
    fun retry(whithDelay: Boolean) {
        if (!enabled) {
            NetworkObserverHandler.getInstance().removeObserver(this)
            return
        }
        if (calling.getAndSet(true))
            return
        showDialog()


        SmartLogger.logDebug("retry")
        if (Utils.isNetworkAvailable(context)) {
            NetworkObserverHandler.getInstance().removeObserver(this)
            Handler().postDelayed({
                if (!enabled)
                    return@postDelayed
                calling.set(false)
//                GamerCity.currentActivity.runOnUiThread({
                    call?.invoke()

//                })
                SmartLogger.logDebug("invoked")

            }, if (whithDelay) delaySecond * 1000 else 0)
        } else {
            calling.set(false)
            NetworkObserverHandler.getInstance().addObserver(this)
        }
    }

    /**
     * ends retry cycle and
     */
    fun finished() {
        numOfFinished -= 1
        if (numOfFinished == 0) {
            disable()
            dismisLoading()
        }
    }

    fun showDialog() {
        if (!dismissed && (dialog == null || !dialog!!.isShowing)) {
            dialog = Dialog(context, R.style.DialogStyle)
            //        alert.setTitle(title);
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog?.setContentView(networkErrorDialog)
            dialog?.setCanceledOnTouchOutside(true)
            dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT)
            dialog?.findViewById<View>(R.id.layMain)?.setOnClickListener {
                dismisLoading()
            }
            dialog?.setOnDismissListener {
                dismissed = true
            }
//            dialog?.setCancelable(false)
            try {
                dialog?.show()

            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun dismisLoading() {
        if (dialog == null)
            return
        if (dialog!!.isShowing)
            try {
                dialog?.dismiss()
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
    }

    override fun getContextForNetworkObserver(): Context {
        return context
    }

    override fun onNetworkStateChange(connected: Boolean) {
        dismissed = false
        if (connected)
            retry(false)
    }

    /**
     * cancels all running task and observers
     * call it in on destroy
     */
    fun disable() {
        enabled = false
        NetworkObserverHandler.getInstance().removeObserver(this)
    }

}
