package com.paraxco.imagetools.Dialog

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.TextView
import com.paraxco.imagetools.R
import com.paraxco.imagetools.Utils.ImageUtils

abstract class  YesNoDialogBase(val layoutRes: Int) {
    private var dialog: Dialog? = null
    var yesAction:(()->Unit)?=null

    fun showDialog(activity:Activity) {

        dialog = Dialog(activity, R.style.FadeInAnimateDialog)
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.attributes.windowAnimations = R.style.FadeInDialogAnimation
        dialog!!.setContentView(layoutRes)
        dialog!!.setCancelable(true)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val wlp = dialog!!.window!!.attributes
        dialog!!.window!!.attributes = wlp

        initDialog()
        //blure
        ImageUtils.blurView(activity, dialog!!.findViewById(R.id.bluredView))
        dialog!!.show()
    }

    private fun initDialog() {
        dialog?.findViewById<TextView>(R.id.txtNote)?.setText(getDialogNote())
        dialog?.findViewById<TextView>(R.id. txtTitle)?.setText(getDialogTitle())

        dialog?.findViewById<View>(R.id.rlDialogBack)?.setOnClickListener {
            dialog?.dismiss()
        }

        dialog?.findViewById<View>(R.id.rlDialog)?.setOnClickListener {
            //to prevent rlDialogBack on click to be performed on rlDialog or background activity
        }

        dialog?.findViewById<View>(R.id.btnCancel)?.setOnClickListener {
            dialog?.dismiss()
        }

        dialog?.findViewById<View>(R.id.btnRemove)?.setOnClickListener {
            doYesAction()
            yesAction?.invoke()
            dialog?.dismiss()
        }
    }

    abstract fun getDialogNote(): Int

    abstract  fun doYesAction()
    abstract fun getDialogTitle(): Int
}
