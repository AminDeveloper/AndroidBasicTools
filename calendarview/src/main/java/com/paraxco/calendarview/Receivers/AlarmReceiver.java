package com.paraxco.calendarview.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.paraxco.calendarview.Helpers.AlarmMagement.Alarm;
import com.paraxco.calendarview.Helpers.AlarmMagement.AlarmHelper;
import com.paraxco.calendarview.Model.CalendarModels.ReminderData;

import java.util.Calendar;


public abstract class AlarmReceiver extends BroadcastReceiver {

    public static final String REMINDER_ID = "reminderID";
    public static final String BROADCAST_ACTION = "CalendarAlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().endsWith(BROADCAST_ACTION))
            return;
        String id = intent.getExtras().getString("idAlarm", " ");
        String time = intent.getExtras().getString("timeAlarm", " ");
        long sCalendar = intent.getExtras().getLong("calendarAlarm", 1);
        Log.e("What is the key? ", id + "   " + time + "sss" + sCalendar);
        //  String id= intent.getExtras().getString("idAlarm");
        // String time= intent.getExtras().getString("timeAlarm");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(sCalendar);
        Alarm alarm = new Alarm(calendar);
        alarm.setId(Integer.valueOf(id));
        ProcesssEvent(context, alarm);
        AlarmHelper.getInstance(context).remove(alarm);
//        createIntent.Myevent(context, alarm);

        /*Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();
        Log.e("aaaaaaaaaaa", "aaaaaaaaaa");
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();*/
    }

    private void ProcesssEvent(Context context, Alarm alarm) {
        ReminderData reminderData = ReminderData.getWithAlamrID(alarm);
        if (reminderData != null)
            showReminderDialog(reminderData, context);
    }

    protected abstract void showReminderDialog(ReminderData reminderData, Context context);
////        Utils.createNotification(context, MainActivity.class, R.drawable.ring_blue,reminderData.getTitle(),reminderData.getFirstHour(),REMINDER_ID+reminderData.getId());
////        Utils.createNotification(context, null, R.drawable.ring_blue,reminderData.getTitle(),reminderData.getFirstHour(),REMINDER_ID+reminderData.getId());
//
//        Toast.makeText(context,reminderData.getTitle(),Toast.LENGTH_LONG).show();
//    }


//    private static CreateIntent createIntent;

//    static void  setCreateIntent(CreateIntent createIntent) {
//        AlarmReceiver.createIntent = createIntent;
//    }
//    interface CreateIntent {
//        void Myevent(Context p, Alarm alarm);
//    }
}