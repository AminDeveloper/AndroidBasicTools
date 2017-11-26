package com.paraxco.calendarview.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.paraxco.calendarview.Helpers.AlarmRegisterHelper;
import com.paraxco.calendarview.Model.CalendarModels.ReminderData;

import java.util.List;

/**
 * Created by Amin on 10/23/2017.
 */

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        List<ReminderData> reminderDatas = ReminderData.getAll();
        for (ReminderData reminderData : reminderDatas) {
            if (reminderData.isEnable()) {
                AlarmRegisterHelper.getInstance().register(context, reminderData);
                reminderData.save();
            }
        }
    }
}
