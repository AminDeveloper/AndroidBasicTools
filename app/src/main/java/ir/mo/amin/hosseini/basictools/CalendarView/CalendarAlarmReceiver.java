package ir.mo.amin.hosseini.basictools.CalendarView;

import android.content.Context;

import ir.mo.amin.hosseini.basictools.R;
import ir.mo.amin.hosseini.calendarview.Receivers.AlarmReceiver;
import ir.mo.amin.hosseini.calendarview.Model.CalendarModels.ReminderData;
import ir.mo.amin.hosseini.commontools.Utils.Utils;

/**
 * Created by Amin on 11/14/2017.
 */

public class CalendarAlarmReceiver extends AlarmReceiver {
    @Override
    protected void showReminderDialog(ReminderData reminderData, Context context) {
        Utils.createNotification(context, CalendarViewTest.class, R.drawable.ring_blue, reminderData.getTitle(), reminderData.getFirstHour(), REMINDER_ID + reminderData.getId());

    }
}
