package com.paraxco.calendarview.Helpers.AlarmMagement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


import com.paraxco.calendarview.Model.CalendarModels.ReminderData;
import com.paraxco.calendarview.R;
import com.paraxco.commontools.Utils.Utils;

import java.util.Calendar;



public class AlarmReceiver extends BroadcastReceiver {

    public static final String REMINDER_ID = "reminderID";

    @Override
    public void onReceive(Context context, Intent intent) {
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
        ReminderData reminderData= ReminderData.getWithAlamrID(alarm);
        showReminderDialog(reminderData, context);
    }
    private void showReminderDialog(ReminderData reminderData, Context context) {
//        Utils.createNotification(context, MainActivity.class, R.drawable.ring_blue,reminderData.getTitle(),reminderData.getFirstHour(),REMINDER_ID+reminderData.getId());
        Utils.createNotification(context, null, R.drawable.ring_blue,reminderData.getTitle(),reminderData.getFirstHour(),REMINDER_ID+reminderData.getId());

        Toast.makeText(context,reminderData.getTitle(),Toast.LENGTH_LONG).show();
    }


//    private static CreateIntent createIntent;

//    static void  setCreateIntent(CreateIntent createIntent) {
//        AlarmReceiver.createIntent = createIntent;
//    }
//    interface CreateIntent {
//        void Myevent(Context p, Alarm alarm);
//    }
}