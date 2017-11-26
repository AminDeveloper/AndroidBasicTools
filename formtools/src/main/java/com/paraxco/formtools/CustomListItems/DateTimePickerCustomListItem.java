package com.paraxco.formtools.CustomListItems;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.paraxco.formtools.R;

import com.paraxco.formtools.CustomListItems.Model.ProfileData;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;


/**
 *
 */

public class DateTimePickerCustomListItem extends TitleTextViewCustomListItem {
    AppCompatActivity activity;
    ViewDataUpdater textDataUpdater;
    private TextView textView;


    public DateTimePickerCustomListItem(String title, AppCompatActivity activity, ViewDataUpdater textDataUpdater) {
        super(R.layout.complete_profile_date_time_picker, title);
        this.activity = activity;
        this.textDataUpdater = textDataUpdater;
    }

//    @Override
//    public int getViewType() {
//        return 7;
//    }

    @Override
    public void initializeView(View view) {
        super.initializeView(view);
        textView = (TextView) view.findViewById(R.id.edit_text_content);
        textView.setText((String) textDataUpdater.getData(getData()));

//        PersianCalendar persianCalendar = new PersianCalendar();


//        final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
//                new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
//                        textView.setText(String.valueOf(year) + String.valueOf(monthOfYear) + String.valueOf(dayOfMonth));
//                    }
//                },
//                persianCalendar.getPersianYear(),
//                persianCalendar.getPersianMonth(),
//                persianCalendar.getPersianDay()
//        );
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickerDialog(activity);


            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                datePickerDialog.show(fragmentManager, "Datepickerdialog");
                showPickerDialog(activity);
            }
        });

    }


    //    private void showPickerDialog(final Context context) {
//
//        ir.hamsaa.persiandatepicker.util.PersianCalendar initDate = new ir.hamsaa.persiandatepicker.util.PersianCalendar();
//        PersianDatePickerDialog picker = new PersianDatePickerDialog(context)
//                .setPositiveButtonString("باشه")
//                .setNegativeButton("بیخیال")
//                .setTodayButton("امروز")
//                .setTodayButtonVisible(true)
//                .setInitDate(initDate)
//                .setMaxYear(1400)
//                .setMinYear(1300)
//                .setActionTextColor(Color.GRAY)
////                .setTypeFace(typeface)
//                .setListener(new Listener() {
//
//                    @Override
//                    public void onDateSelected(ir.hamsaa.persiandatepicker.util.PersianCalendar persianCalendar) {
//                        Toast.makeText(context, persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay(), Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onDisimised() {
//
//                    }
//                });
//
//        picker.show();
//    }
    private void showPickerDialog(final AppCompatActivity context) {

        PersianCalendar initDate = new PersianCalendar();
        initDate.setTimeInMillis(System.currentTimeMillis());
        int currentYear = initDate.getPersianYear();
        try {
            initDate.setPersianDate(getYear((String) textView.getText()), getMonth((String) textView.getText()), getDay((String) textView.getText()));
        } catch (StringIndexOutOfBoundsException e) {

        }
        PersianDatePickerDialog picker = new PersianDatePickerDialog(activity)
                .setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setInitDate(initDate)
                .setMaxYear(currentYear)
                .setMinYear(1300)
                .setActionTextColor(Color.GRAY)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        String dateString = persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay();
                        textDataUpdater.setData(dateString, getData());
                        textView.setText(dateString);
                    }

                    @Override
                    public void onDisimised() {

                    }
                });

        picker.show();
    }

    private int getYear(String text) {
        return Integer.parseInt(text.substring(0, text.indexOf("/")));
    }

    private int getMonth(String text) {
        return Integer.parseInt(text.substring(text.indexOf("/") + 1, text.lastIndexOf("/")));
    }

    private int getDay(String text) {
        return Integer.parseInt(text.substring(text.lastIndexOf("/") + 1, text.length()));
    }

    public void showDialog(AppCompatActivity contexct) {
//        final DialogFragment dialog = new DTPDialogFragment();
////        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(true);
////        dialog.setContentView(R.layout.date_picker);
//        dialog.show(contexct.getSupportFragmentManager(), "tag");
//
////        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
////        text.setText(msg);
////
////        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
////        dialogButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                dialog.dismiss();
////            }
////        });
//
////        dialog.show();

    }


    @Override
    public EditText requestFocus() {
        return null;
    }

    @Override
    public void resetData(ProfileData profileData) {

    }
}
