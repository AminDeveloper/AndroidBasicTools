package ir.mo.amin.hosseini.calendarview.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ir.mo.amin.hosseini.calendarview.Helpers.AlarmRegisterHelper;
import ir.mo.amin.hosseini.calendarview.Model.CalendarModels.ReminderData;

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
