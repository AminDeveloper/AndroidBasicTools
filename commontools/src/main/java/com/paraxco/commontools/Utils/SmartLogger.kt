package com.paraxco.commontools.Utils

import android.content.Context
import java.util.logging.Logger


/**
 * Created by Amin on 10/15/2017.
 */
class SmartLogger {

    companion object {
        private var context: Context?=null

        private val LOGGER_NAME = "SmartLogger"

        fun initLogger(context: Context){
            this.context=context
        }
        fun releaseContext(){
            context=null
        }

        fun logDebug(msg: String? = "") {
            Logger.getLogger(LOGGER_NAME).warning(getHeaders() + msg)
        }

        private fun getHeaders(): String {
            //todo add real instance class name
            var element = getElement()
            val className: String = element.className
            val methodName: String = element.methodName
            val lineNumber: String = element.lineNumber.toString()
            return "$className\n$methodName(Line:$lineNumber) Version:${getVersion()}:\n"
        }

        private fun getElement(): StackTraceElement {
            var inThis = false
            Thread.currentThread().stackTrace.forEach {
                //find first element witch is not this class
                if (it.className == SmartLogger.javaClass.name)
                    inThis = true
                else
                    if (inThis == true) {
                        return it
                    }
            }


            return Thread.currentThread().stackTrace[4]
        }


        private fun getVersion(): String {
            val manager = context?.packageManager
            val info = manager?.getPackageInfo(context?.packageName, 0)
//            Toast.makeText(this,
//                    "PackageName = " + info.packageName + "\nVersionCode = "
//                            + info.versionCode + "\nVersionName = "
//                            + info.versionName + "\nPermissions = " + info.permissions, Toast.LENGTH_SHORT).show()
            val versionCode = info?.versionCode
            val versionName = info?.versionName
            return "$versionName($versionCode)"
        }
    }


}