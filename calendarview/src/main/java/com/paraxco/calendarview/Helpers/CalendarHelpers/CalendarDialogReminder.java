package com.paraxco.calendarview.Helpers.CalendarHelpers;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.paraxco.calendarview.Fragments.CalendarFragments.CalendarPages.CalendarDateFragment;
import com.paraxco.calendarview.Helpers.AlarmRegisterHelper;
import com.paraxco.calendarview.Helpers.Observers.ReminderObserverHandler;
import com.paraxco.calendarview.Model.CalendarModels.CalendarDayItem;
import com.paraxco.calendarview.Model.CalendarModels.ReminderData;
import com.paraxco.calendarview.R;
import com.paraxco.commontools.Utils.Utils;
import com.paraxco.listtools.ListTools.Adapter.RecyclerView.RecyclerViewAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;


public class CalendarDialogReminder {
    private String requestedHour = null;
    private TextView showDate, description, title, okButtom, first_time_title, first_time_value, last_time_title, last_time_value;
    private EditText remind;
    private AlertDialog alertDialog;
    private Button addReminder, removeReminder, changeReminder;
    private View viewadd;
    private NumberPicker hourPicker, minPicker;
    private Long date;
    private LinkedList<CalendarDayItem> calendarItems;
    private  RecyclerViewAdapter reminderAdapter;
    CalendarDateFragment calendarDateFragment;
    ReminderData reminderData;//for edit and remove
    //    private CalendarViewManager calendarViewManager;
//    RemindersListDialuge remindersListDialuge;

    public CalendarDialogReminder(Context context, final CalendarDateFragment calendarDateFragment, final PersianCalendar thisDay) {
        constructWithCalendar(context, calendarDateFragment, thisDay);
    }

    public CalendarDialogReminder(@NotNull Context context, @NotNull CalendarDateFragment calendarDateFragment, @NotNull PersianCalendar thisDay, String hourText) {
        requestedHour = hourText;
        constructWithCalendar(context, calendarDateFragment, thisDay);

    }

//    public CalendarDialogReminder(Context context, CalendarDateFragment calendarDateFragment, PersianCalendar thisDay, int id) {
//        this(context, calendarDateFragment, thisDay);
//        if (id != 24)
//            last_time_value.setText(String.valueOf(id + 1 + ":00"));
//        else
//            last_time_value.setText(String.valueOf(id + ":00"));
//        first_time_value.setText(String.valueOf(id + ":00"));
//    }


    public CalendarDialogReminder(Context context, final CalendarDateFragment calendarDateFragment, final ReminderData reminderData) {
        constructReminderDate(context, calendarDateFragment, reminderData);
    }


//    public CalendarDialogReminder(@NotNull Context context, @NotNull CalendarDateFragment calendarDateFragment, @NotNull RemindersListDialuge remindersListDialuge, @NotNull ReminderData reminderData) {
//        this(context, calendarDateFragment, reminderData);
//        this.remindersListDialuge=remindersListDialuge;
//    }
//
//    public CalendarDialogReminder(@NotNull Context context, @NotNull CalendarDateFragment calendarDateFragment, @NotNull RemindersListDialuge remindersListDialuge, @NotNull PersianCalendar persianCalendar) {
//        constructWithCalendar(context,calendarDateFragment,persianCalendar);
//        this.remindersListDialuge=remindersListDialuge;
//    }

    private void construct(Context context, final CalendarDateFragment calendarDateFragment) {
        init(context);
        this.calendarDateFragment = calendarDateFragment;
    }

    private void constructReminderDate(Context context, final CalendarDateFragment calendarDateFragment, final ReminderData reminderData) {
        construct(context, calendarDateFragment);
        this.reminderData = reminderData;
        date = reminderData.getDate();
        title.setText(reminderData.getTitle());
        description.setText(reminderData.getDescription());
        first_time_value.setText(reminderData.getFirstHour());
        last_time_value.setText(reminderData.getSecondHour());
        remind.setText(reminderData.getRemind());
        PersianCalendar dialogDate = new PersianCalendar();
        dialogDate.setTimeInMillis(reminderData.getDate());
        showDate.setText(dialogDate.getPersianLongDate());
        addReminder.setVisibility(View.GONE);
        changeReminder.setVisibility(View.VISIBLE);
        removeReminder.setVisibility(View.VISIBLE);
        removeReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reminderData.remove();
                AlarmRegisterHelper.getInstance().remove(view.getContext(), reminderData);

                notifyReminderList(reminderData);
                Toast.makeText(view.getContext(), "با موفقیت حذف شد", Toast.LENGTH_SHORT).show();
//                calendarViewManager.refreshReminders(reminderData);
                alertDialog.cancel();
            }
        });
        changeReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveContents(view, reminderData);

            }
        });
    }

    private void constructWithCalendar(final Context context, final CalendarDateFragment calendarDateFragment, final PersianCalendar thisDay) {
        construct(context, calendarDateFragment);
        this.date = thisDay.getTimeInMillis();
        showDate.setText(thisDay.getPersianLongDate());
        showDate.setText(Utils.localeNumber(showDate.getText().toString()));
        showDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickerDialog(v.getContext());
            }
        });
        showDate.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        addReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveContents(context, view);
            }
        });

        if (requestedHour != null) {
            first_time_value.setText(Utils.localeNumber((requestedHour.length() == 1 ? "0" : "") + requestedHour + ":00"));
            last_time_value.setText(Utils.localeNumber((requestedHour.length() == 1 ? "0" : "") + requestedHour + ":00"));

        }
    }

    private void saveContents(Context context, View view) {
        ReminderData reminderData = new ReminderData();
        reminderData.setTitle(title.getText().toString());
        reminderData.setDescription(description.getText().toString());
        reminderData.setFirstHour(first_time_value.getText().toString());
        reminderData.setSecondHour(last_time_value.getText().toString());
        reminderData.setRemind(remind.getText().toString());
        reminderData.setDate(date);

        AlarmRegisterHelper.getInstance().register(context, reminderData);//initialtes Alarm ID
        reminderData.save();
        notifyReminderList(reminderData);

        Toast.makeText(view.getContext(), "با موفقیت افزوده شد", Toast.LENGTH_SHORT).show();
//                calendarViewManager.refreshReminders(reminderData);
        alertDialog.cancel();
    }

    private void notifyReminderList(ReminderData reminderData) {
//        CalendarListHelper.calendarReminderItems.clear();
//        ArrayList<ReminderData> listReminder = ReminderData.getAllForDay(CalendarListHelper.shareDate.getTimeInMillis());
//        if (listReminder.size() != 0)
//            for (int i = 0; i < listReminder.size(); i++)
//                CalendarListHelper.calendarReminderItems.add(new CalendarReminderItem(listReminder.get(i), calendarDateFragment));
//        reminderAdapter = DataItemListAdapter.initializeLinearRecyclerView(CalendarListHelper.recyclerViewEvent, CalendarListHelper.calendarReminderItems);
//        reminderAdapter.notifyDataSetChanged();

//        calendarDateFragment.refreshView();

        ReminderObserverHandler.Companion.getInstance().informObservers(reminderData);
    }

    private void saveContents(View view, ReminderData reminderData) {
        AlarmRegisterHelper.getInstance().remove(view.getContext(), reminderData);

        reminderData.setTitle(title.getText().toString());
        reminderData.setDescription(description.getText().toString());
        reminderData.setFirstHour(first_time_value.getText().toString());
        reminderData.setSecondHour(last_time_value.getText().toString());
        reminderData.setRemind(remind.getText().toString());
        reminderData.setDate(date);

        if (reminderData.isEnable())
            AlarmRegisterHelper.getInstance().register(view.getContext(), reminderData);
        reminderData.save();
        notifyReminderList(reminderData);

//                calendarViewManager.refreshReminders(reminderData);
        Toast.makeText(view.getContext(), "با موفقیت ویرایش شد", Toast.LENGTH_SHORT).show();

        alertDialog.cancel();
    }


    public void showDialog() {
        alertDialog.show();
    }


    private void showPickerDialog(final Context c) {

        PersianDatePickerDialog picker = new PersianDatePickerDialog(c)
                .setPositiveButtonString("تایید")
                .setNegativeButton("لغو")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMaxYear(1398)
                .setMinYear(1385)
                .setActionTextColor(Color.GRAY)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        showDate.setText(persianCalendar.getPersianLongDate());
                        date = persianCalendar.getTimeInMillis();
                    }

                    @Override
                    public void onDisimised() {

                    }
                });

        picker.show();
    }


    private void init(Context context) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewadd = mInflater.inflate(R.layout.calendar_dialog, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(viewadd);
        alertDialog = builder.create();
        alertDialog.setCancelable(true);
        showDate = (TextView) viewadd.findViewById(R.id.show_date);
        description = (TextView) viewadd.findViewById(R.id.description);

        title = (TextView) viewadd.findViewById(R.id.title_dialog_reminder);
        first_time_title = (TextView) viewadd.findViewById(R.id.first_time_title);
        first_time_value = (TextView) viewadd.findViewById(R.id.first_time_value);
        last_time_title = (TextView) viewadd.findViewById(R.id.last_time_title);
        last_time_value = (TextView) viewadd.findViewById(R.id.last_time_value);

        first_time_value.setText(Utils.localeNumber("00:00"));
        last_time_value.setText(Utils.localeNumber("00:00"));


        remind = (EditText) viewadd.findViewById(R.id.remind);
        addReminder = (Button) viewadd.findViewById(R.id.add_reminder);
        changeReminder = (Button) viewadd.findViewById(R.id.change_reminder);
        removeReminder = (Button) viewadd.findViewById(R.id.delete_reminder);
        first_time_title.setOnClickListener(dialogPickerListener(context, 0));
        first_time_value.setOnClickListener(dialogPickerListener(context, 0));
        last_time_title.setOnClickListener(dialogPickerListener(context, 1));
        last_time_value.setOnClickListener(dialogPickerListener(context, 1));
    }


    private void showDialogNumber(final Context context, final int id) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.calendar_dialog_picker);
        okButtom = (TextView) dialog.findViewById(R.id.ok_picker);
        hourPicker = (NumberPicker) dialog.findViewById(R.id.hourPicker);
        minPicker = (NumberPicker) dialog.findViewById(R.id.minPicker);

        String[] listPersianNumber = new String[60];
        for (int i = 0; i < 60; i++) {
            if (i > 9)
                listPersianNumber[i] = Utils.localeNumber(String.valueOf(i));
            else
                listPersianNumber[i] = Utils.localeNumber(String.valueOf("0" + i));
        }
        hourPicker.setDisplayedValues(listPersianNumber);
        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(23);
        minPicker.setDisplayedValues(listPersianNumber);
        minPicker.setMinValue(0);
        minPicker.setMaxValue(listPersianNumber.length - 1);

        if (!first_time_value.getText().toString().equals("-----") && !last_time_value.getText().toString().equals("-----"))
            if (id != 1) {
                hourPicker.setValue(Integer.valueOf(first_time_value.getText().toString().substring(0, 2)));
                minPicker.setValue(Integer.valueOf(first_time_value.getText().toString().substring(3, 5)));
            } else {
                hourPicker.setValue(Integer.valueOf(last_time_value.getText().toString().substring(0, 2)));
                minPicker.setValue(Integer.valueOf(last_time_value.getText().toString().substring(3, 5)));
            }

        okButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hour, min;
                hour = hourPicker.getValue() > 9 ? String.valueOf(hourPicker.getValue()) : "0" + hourPicker.getValue();
                min = minPicker.getValue() > 9 ? String.valueOf(minPicker.getValue()) : "0" + minPicker.getValue();

                if (id != 1) {
                    first_time_value.setText(String.valueOf(hour + ":" + min));
                    first_time_value.setText(Utils.localeNumber(first_time_value.getText().toString()));
                    last_time_value.setText(String.valueOf(hour + ":" + min));
                    last_time_value.setText(Utils.localeNumber(last_time_value.getText().toString()));
                } else {
                    last_time_value.setText(String.valueOf(hour + ":" + min));
                    last_time_value.setText(Utils.localeNumber(last_time_value.getText().toString()));
                }


                dialog.cancel();
            }
        });
        dialog.show();
    }


    private View.OnClickListener dialogPickerListener(final Context context, final int id) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogNumber(context, id);
            }
        };
    }

}
