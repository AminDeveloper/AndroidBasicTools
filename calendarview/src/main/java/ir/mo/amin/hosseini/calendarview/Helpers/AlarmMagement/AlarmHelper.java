package ir.mo.amin.hosseini.calendarview.Helpers.AlarmMagement;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;


import ir.mo.amin.hosseini.calendarview.Receivers.AlarmReceiver;

import java.util.ArrayList;
import java.util.List;


public class AlarmHelper {
    private Context context;
    private AlarmManager alarmManager;
//    private OnEventAlarmListener alarmEvent;
    private Alarm alarm;
    private ArrayList<Alarm> alarmList = new ArrayList<>();
//    AlarmReceiver alarmReceiver = new AlarmReceiver();
    private static AlarmHelper instance = null;

    public static AlarmHelper getInstance(Context context) {
        if (instance == null) {
            instance = new AlarmHelper(context);
            return instance;
        } else
            return instance;
    }


    private AlarmHelper(final Context context) {
        this.context = context;
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        AlarmReceiver.setCreateIntent(this);
    }


    public AlarmHelper startAlarm(Alarm alarm) {
//        final Intent intent = new Intent(context, AlarmReceiver.class);

        Intent intent = new Intent("CalendarAlarmReceiver");

        intent.putExtra("idAlarm", String.valueOf(alarm.getId()));
        intent.putExtra("timeAlarm", String.valueOf(alarm.getTime()));
        intent.putExtra("calendarAlarm", alarm.getCalendar());
        Log.e("eeeee11111", alarm.getId() + "");

        start(PendingIntent.getBroadcast(context, alarm.getId(), intent, 0), alarm.getTime());
        this.alarm=alarm;
        alarmList.add(alarm);
        return getInstance(context);
    }


    public void startAlarm(List<Alarm> alarms) {
        for (int i = 0; i < alarms.size(); i++) {
            final Intent intent = new Intent(context, AlarmReceiver.class);
            Log.e("eeeee", alarms.get(i).getId() + "");
            start(PendingIntent.getBroadcast(context, alarms.get(i).getId(), intent, 0), alarms.get(i).getTime());
            alarmList.add(alarms.get(i));
        }
    }


    public void remove(Alarm alarm) {

      remove(alarm.getId());
    }

    public void remove(int id) {
        final Intent intent = new Intent(context, AlarmReceiver.class);
        cancel(PendingIntent.getBroadcast(context,  id, intent, 0));
    }
    public void clear() {
        for (Alarm alarm : alarmList) {
            remove(alarm);
        }
    }

//    public void setOnEventAlarmListener(OnEventAlarmListener alarmEvent) {
//        this.alarmEvent = alarmEvent;
//
//    }

    public interface OnEventAlarmListener {
        public void onEvent(Context context, Alarm alarm);
    }

    private void start(PendingIntent pendingIntent, long time) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(time, pendingIntent), pendingIntent);
        } else
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 15 * 1000 * 60, pendingIntent);
    }

    private void cancel(PendingIntent pendingIntent) {
        alarmManager.cancel(pendingIntent);
    }


//    @Override
//    public void Myevent(Context p,Alarm alarm) {
//        alarmEvent.onEvent(context,alarm);
//    }
}
