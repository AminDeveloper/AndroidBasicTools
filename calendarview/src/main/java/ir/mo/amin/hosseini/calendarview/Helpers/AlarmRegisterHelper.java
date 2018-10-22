package ir.mo.amin.hosseini.calendarview.Helpers;

import android.content.Context;


import ir.mo.amin.hosseini.calendarview.Helpers.AlarmMagement.Alarm;
import ir.mo.amin.hosseini.calendarview.Helpers.AlarmMagement.AlarmHelper;
import ir.mo.amin.hosseini.calendarview.Model.CalendarModels.ReminderData;

import ir.hamsaa.persiandatepicker.util.PersianCalendar;

/**
 * Created by Amin on 10/23/2017.
 */

public class AlarmRegisterHelper {

    static AlarmRegisterHelper alarmRegisterHelper;

    private AlarmRegisterHelper() {

    }

    public static AlarmRegisterHelper getInstance() {
        if (alarmRegisterHelper == null)
            alarmRegisterHelper = new AlarmRegisterHelper();
        return alarmRegisterHelper;
    }

    public void register(Context context, final ReminderData reminderData) {
        if(getRemindTimeInMilli(reminderData)<System.currentTimeMillis())//do not register past reminders
            return;

        Alarm alarm = new Alarm(new PersianCalendar(getRemindTimeInMilli(reminderData)));

        AlarmHelper.getInstance(context).startAlarm(alarm);
//                .setOnEventAlarmListener(new AlarmHelper.OnEventAlarmListener() {
//                    @Override
//                    public void onEvent(Context context, Alarm alarm) {
//                        Log.e("ss", "EEEEEEEEEEEEEEEEEee" + alarm.getId());
//                        showReminderDialog(reminderData, context);
//                    }
//                });
        reminderData.setAlarmID(alarm.getId());
    }

    private long getRemindTimeInMilli(ReminderData reminderData) {
        long beforeMillis =0;
        try {
             beforeMillis = Long.parseLong(reminderData.getRemind()) * 60 * 1000;
        } catch (NumberFormatException e) {

        }
        return reminderData.getFirstDate().getTimeInMillis() - beforeMillis;
    }

//    private void showReminderDialog(ReminderData reminderData, Context context) {
//        Toast.makeText(context,reminderData.getTitle(),Toast.LENGTH_LONG).show();
//    }

    public void remove(Context context, ReminderData reminderData) {
        AlarmHelper.getInstance(context).remove(reminderData.getAlarmID());
        reminderData.setAlarmID(-1);
    }
}
