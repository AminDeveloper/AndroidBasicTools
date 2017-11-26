package com.paraxco.calendarview.Model.CalendarModels;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.paraxco.calendarview.Fragments.CalendarFragments.CalendarPages.CalendarDateFragment;
import com.paraxco.calendarview.Helpers.AlarmRegisterHelper;
import com.paraxco.calendarview.Helpers.CalendarHelpers.CalendarDialogReminder;
import com.paraxco.calendarview.Helpers.Observers.ReminderObserverHandler;
import com.paraxco.calendarview.R;
import com.paraxco.listtools.ListTools.DataItem.DataItemBase;


public class CalendarReminderItem extends DataItemBase {

    private TextView time;
    private TextView description;
    private ImageView icon;
    private View indicator;
    String title;
    //  String sTime;
    //  String sDescription;
    //  PersianCalendar date;
    ReminderData reminderData;
    private CalendarDateFragment calendarDateFragment;
    private ImageView enableIcon;

    public CalendarReminderItem(ReminderData reminderData, CalendarDateFragment calendarDateFragment) {
        super(R.layout.calendar_reminder_item);
        this.calendarDateFragment = calendarDateFragment;
        // this.sTime = reminderData.getDate().toString();
        //  this.sDescription = reminderData.getDescription();
        // this.date = new PersianCalendar(reminderData.getDate());
        this.reminderData = reminderData;
    }

    public CalendarReminderItem() {
        super(R.layout.calendar_reminder_item);
        this.title = "عنوان";
    }

    @Override
    public void initializeView(final View view) {
        indicator = view.findViewById(R.id.indicator);
        enableIcon = (ImageView) view.findViewById(R.id.enable_icon);
        time = (TextView) view.findViewById(R.id.time);
        description = (TextView) view.findViewById(R.id.description);
        enableIcon.setImageResource(reminderData.isEnable() ? R.drawable.ring_blue : R.drawable.ring_black);
        time.setText(String.valueOf(reminderData.getFirstHour()));
        description.setText(reminderData.getDescription());
//        view.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                ArrayList<ReminderData> listReminder = ReminderData.getAllForDay(CalendarListHelper.shareDate.getTimeInMillis());
//           //     CalendarReminderItem calendarReminderItem = new CalendarReminderItem();
//              //  calendarReminderItem.title = sDescription;
//                new CalendarDialogReminder(reminderData,calendarDateFragment, view.getContext()).showDialog();
//                // Log.e("eeeeeeeeeeeee",date.getTimeInMillis()+"");
//                return false;
//            }
//        });
        enableIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminderData.setEnable(!reminderData.isEnable());
                enableIcon.setImageResource(reminderData.isEnable() ? R.drawable.ring_blue : R.drawable.ring_black);
                if(reminderData.isEnable()){
                    AlarmRegisterHelper.getInstance().register(enableIcon.getContext(), reminderData);

                }else {
                    AlarmRegisterHelper.getInstance().remove(enableIcon.getContext(), reminderData);

                }
                reminderData.save();
                ReminderObserverHandler.Companion.getInstance().informObservers(reminderData);

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ArrayList<ReminderData> listReminder = ReminderData.getAllForDay(CalendarListHelper.shareDate.getTimeInMillis());
                //     CalendarReminderItem calendarReminderItem = new CalendarReminderItem();
                //  calendarReminderItem.title = sDescription;
                new CalendarDialogReminder(view.getContext(), calendarDateFragment, reminderData).showDialog();
                // Log.e("eeeeeeeeeeeee",date.getTimeInMillis()+"");
            }
        });

    }
}
