package com.paraxco.basictools.CalendarView;

import android.content.Context;

import com.paraxco.basictools.R;
import com.paraxco.calendarview.Receivers.AlarmReceiver;
import com.paraxco.calendarview.Model.CalendarModels.ReminderData;
import com.paraxco.commontools.Utils.Utils;

/**
 * Created by Amin on 11/14/2017.
 */

public class CalendarAlarmReceiver extends AlarmReceiver {
    @Override
    protected void showReminderDialog(ReminderData reminderData, Context context) {
        Utils.createNotification(context, CalendarViewTest.class, R.drawable.ring_blue, reminderData.getTitle(), reminderData.getFirstHour(), REMINDER_ID + reminderData.getId());

    }
}
