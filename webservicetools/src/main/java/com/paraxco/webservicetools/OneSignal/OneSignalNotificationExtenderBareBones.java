//package com.paraxco.webservicetools.OneSignal;
//
//import android.util.Log;
//
//import com.onesignal.NotificationExtenderService;
//import com.onesignal.OSNotificationReceivedResult;
//
//import org.json.JSONObject;
//
//public class OneSignalNotificationExtenderBareBones extends NotificationExtenderService {
//    public static final String EVENT_TAG = "event_tag";
//
//
//    public static final String NOTIFICATION_ACTION = "notification_action";
//
//    @Override
//    protected boolean onNotificationProcessing(OSNotificationReceivedResult receivedResult) {
//        JSONObject poinJson = receivedResult.payload.additionalData;
//        System.out.println("Location_notif" + poinJson.toString());
//        System.out.println("Location_notif body:" + receivedResult.payload.body);
//        Log.d("Location_notif", poinJson.toString());
//        String body = receivedResult.payload.body;
//
//        // Return true to stop the notification from displaying.
//        return true;
//    }
//
//
//}