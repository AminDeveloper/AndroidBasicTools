package com.paraxco.commontools.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.logging.Logger;


public class SmartLogger {
    private static SmartLogger instance;
    public static SmartLogger Companion;//this is just for compatibility issue after converted from kotlin

    public static SmartLogger getInstance() {
        if (instance == null) {
            instance = new SmartLogger();
            Companion = instance;
        }
        return instance;
    }

    public static void logDebug(String msg) {
        getInstance().instanceLogDebug(msg);
    }
    public static void logDebug() {
        getInstance().instanceLogDebug("");
    }

    private Context context = null;

    private String LOGGER_NAME = "SmartLogger";

    void initLogger(Context context) {
        this.context = context;
    }

    void releaseContext() {
        context = null;
    }

    public void instanceLogDebug(String msg) {
        Logger.getLogger(LOGGER_NAME).warning(getHeaders() + msg);
    }

    private String getHeaders() {
        //todo add real instance class name
        StackTraceElement element = getElement();
        String className = element.getClassName();
        String methodName = element.getClassName();
        String lineNumber = String.valueOf(element.getLineNumber());
        return className+"\n"+methodName+"(Line:"+lineNumber+") Version:"+getVersion()+"+\n";
    }

    private StackTraceElement getElement() {
        boolean inThis = false;
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            //find first element witch is not this class
            if (stackTraceElement.getClassName().endsWith(SmartLogger.class.getName()))
                inThis = true;
            else if (inThis) {
                return stackTraceElement;
            }
        }

        return Thread.currentThread().getStackTrace()[4];
    }


    private String getVersion() {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);

//            Toast.makeText(this,
//                    "PackageName = " + info.packageName + "\nVersionCode = "
//                            + info.versionCode + "\nVersionName = "
//                            + info.versionName + "\nPermissions = " + info.permissions, Toast.LENGTH_SHORT).show()
            String versionCode = String.valueOf(info.versionCode);
            String versionName = info.versionName;
            return versionName + "(" + versionCode + ")";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "PackageManager.NameNotFoundException";
    }


}