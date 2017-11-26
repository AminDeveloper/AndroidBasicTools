package com.paraxco.calendarview.Helpers.CalendarHelpers.CalendarListHelpers;//package com.paraxco.app63167.Helpers.CalendarHelpers.CalendarListHelpers;
//
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//
//import com.paraxco.app63167.Fragments.CalendarFragments.CalendarPages.CalendarDateFragment;
//import com.paraxco.app63167.Helpers.ListTools.Adapter.DataItemListAdapter;
//import com.paraxco.app63167.Helpers.Observers.ObserverHandlerBase;
//import com.paraxco.app63167.Helpers.Utils;
//import com.paraxco.app63167.Interfaces.ValueContainer;
//import com.paraxco.app63167.Model.CalendarModels.CalendarDayItem;
//import com.paraxco.app63167.Model.CalendarModels.CalendarReminderItem;
//import com.paraxco.app63167.Model.CalendarModels.ReminderData;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.LinkedList;
//
//import ir.hamsaa.persiandatepicker.util.PersianCalendar;
//
///**
// *
// */
//
//public class CalendarListHelper implements ValueContainer<Integer>, ObserverHandlerBase.Observer {
//    PersianCalendar persianCalendar;
//    private DataItemListAdapter adapter;
//    private RecyclerView recyclerView;
//    private LinkedList<CalendarDayItem> calendarItems;
//    private DataItemListAdapter reminderAdapter;
//    private RecyclerView recyclerViewEvent;
////        public  PersianCalendar shareDate;
//    private ValueContainer<PersianCalendar> dateChangeListener;
//    private boolean horrizental = true;
//    private boolean weekly = false;
//    private int weekNumber;
//    private int layout;
//    public ArrayList<CalendarReminderItem> calendarReminderItems = new ArrayList<>();
//    GotoWeekListener gotoWeekListener;
//    private CalendarDateFragment calendarDateFragment;
//
////    private TextView todayTextView;
//
////    public CalendarListHelper(int year, int month, int day) {
////        persianCalendar = new PersianCalendar();
////        persianCalendar.setPersianDate(year, month, day);
////    }
//
//    public CalendarListHelper(PersianCalendar persianCalendar, CalendarDateFragment calendarDateFragment, int layout) {
//        this.calendarDateFragment = calendarDateFragment;
//        this.persianCalendar = new PersianCalendar(persianCalendar.getTimeInMillis());
//        this.layout = layout;
//
//    }
//
//    public CalendarListHelper(PersianCalendar persianCalendar, CalendarDateFragment calendarDateFragment,RecyclerView recyclerViewEvent, int layout) {
//        this.calendarDateFragment = calendarDateFragment;
//        this.persianCalendar = new PersianCalendar(persianCalendar.getTimeInMillis());
//        this.layout = layout;
//        this. recyclerViewEvent= recyclerViewEvent;
//
//    }
//
//    public void visualizeOnRecyclerView(RecyclerView recyclerView) {
//        this.recyclerView = recyclerView;
//        fillCalendarItems();
//        if (calendarItems.size() == 0)
//            return;
//        if (horrizental)
//            adapter = DataItemListAdapter.initializeHorrizentalRecyclerView(recyclerView, calendarItems);
//        else {
//            checkFristDayOfWeek();
//            checkLastDayOfWeek();
//            adapter = DataItemListAdapter.initializeGridRecyclerView(recyclerView, 7, calendarItems);
//        }
////        scrollToDay();
////        if (dateChangeListener != null)
////            dateChangeListener.setValue(persianCalendar);
//    }
//
//
//    private void fillCalendarItems() {
//        calendarItems = new LinkedList<>();
//        PersianCalendar tempCalendar = new PersianCalendar();
//        tempCalendar.setPersianDate(persianCalendar.getPersianYear(), persianCalendar.getPersianMonth(), 1);
//        int currentWeek = 1;
//        do {
//            CalendarDayItem currentDay = new CalendarDayItem(tempCalendar, calendarDateFragment, this, layout);
//            if (weekly) {
//                if (weekNumber == currentWeek)
//                    calendarItems.add(currentDay);
//                if (currentDay.getTheyOfWeek() == Calendar.FRIDAY)
//                    currentWeek++;
//                if (currentWeek > weekNumber)
//                    break;
//            } else
//                calendarItems.add(currentDay);
//            tempCalendar.addPersianDate(Calendar.DATE, 1);
//        } while (tempCalendar.getPersianMonth() == persianCalendar.getPersianMonth());
//    }
//
//    @Override
//    public void observeReminderChange() {
//       refreshCalendarEvents();
//    }
//
//    private void checkFristDayOfWeek() {
//
//        while (calendarItems.getFirst().getTheyOfWeek() != Calendar.SATURDAY) {
//            final PersianCalendar tempCalendar = new PersianCalendar(calendarItems.getFirst().getThisDay().getTimeInMillis());
//            tempCalendar.addPersianDate(Calendar.DATE, -1);
//            CalendarDayItem currentDay = new CalendarDayItem(tempCalendar, calendarDateFragment, this, layout);
//            currentDay.setLink(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (gotoWeekListener != null)
//                        gotoWeekListener.gotoWeek(tempCalendar);
//                }
//            });
//
//            calendarItems.addFirst(currentDay);
//        }
//
////        if (calendarItems.get(0).getTheyOfWeek() != Calendar.SATURDAY) {
////            int dayBeforeSaturday = calendarItems.get(0).getTheyOfWeek();
////            for (int i = 0; i < dayBeforeSaturday; i++) {
////                calendarItems.addFirst(new CalendarDayItem());
////            }
////        }
//    }
//
//    private void checkLastDayOfWeek() {
//        while (calendarItems.getLast().getTheyOfWeek() != Calendar.FRIDAY) {
//            final PersianCalendar tempCalendar = new PersianCalendar(calendarItems.getLast().getThisDay().getTimeInMillis());
//            tempCalendar.addPersianDate(Calendar.DATE, 1);
//            CalendarDayItem currentDay = new CalendarDayItem(tempCalendar, calendarDateFragment, this, layout);
//            currentDay.setLink(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (gotoWeekListener != null)
//                        gotoWeekListener.gotoWeek(tempCalendar);
//                }
//            });
//            calendarItems.add(currentDay);
//        }
//    }
//
//    public void selectDay(int day) {
//        persianCalendar.setPersianDate(persianCalendar.getPersianYear(), persianCalendar.getPersianMonth(), day);
////        scrollToDay();
//        if (dateChangeListener != null)
//            dateChangeListener.setValue(persianCalendar);
////        todayTextView.setText(Utils.localeNumber(persianCalendar.getPersianShortDate()));
//        refreshCalendarEvents();
//    }
//
//    private void refreshCalendarEvents() {
//
//        if(recyclerViewEvent==null)
//            return;
//        calendarReminderItems.clear();
//        ArrayList<ReminderData> listReminder = ReminderData.getAllForDay(getDate().getTimeInMillis());
//        if (listReminder.size() != 0)
//            for (int i = 0; i < listReminder.size(); i++)
//                calendarReminderItems.add(new CalendarReminderItem(listReminder.get(i), calendarDateFragment));
//        reminderAdapter = DataItemListAdapter.initializeLinearRecyclerView(recyclerViewEvent, calendarReminderItems);
//        reminderAdapter.notifyDataSetChanged();
//
//        adapter.notifyDataSetChanged();
//    }
//
//    private void scrollToDay() {
////        int day = persianCalendar.getPersianDay();
////        ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(day - 1, getOffset(day));
//
//    }
//
//    private void scrollToDay(int day) {
//        ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(day - 1, getOffset(day));
//    }
//
//    public void setDateChangeListener(ValueContainer<PersianCalendar> dateChangeListener) {
////        shareDate=dateChangeListener.getValue();
//        this.dateChangeListener = dateChangeListener;
//    }
//
//    public void selectDate(int year, int month, int day) {
//        persianCalendar.setPersianDate(year, month, day);
//
//        visualizeOnRecyclerView(recyclerView);
//        scrollToDay();
//        adapter.notifyDataSetChanged();
//
//    }
//
//    public void selectDate(PersianCalendar persianCalendar) {
//        this.persianCalendar = new PersianCalendar(persianCalendar.getTimeInMillis());
//        if (recyclerView != null) {
//            visualizeOnRecyclerView(recyclerView);
//            scrollToDay();
//            if (adapter != null)//if no item!
//                adapter.notifyDataSetChanged();
//        }
//    }
//
//    public PersianCalendar getDate() {
//        return persianCalendar;
//    }
//
//    //    @Override
////    public Integer getValue() {
////        return persianCalendar.getPersianDay();
////    }
//    @Override
//    public Integer getValue() {
//        if (dateChangeListener.getValue().getPersianMonth() == persianCalendar.getPersianMonth() && dateChangeListener.getValue().getPersianYear() == persianCalendar.getPersianYear())
//            return dateChangeListener.getValue().getPersianDay();
//        else return -1;
//    }
//
//    @Override
//    public void setValue(Integer value) {
//        selectDay(value);
//    }
//
//    private int getOffset(int day) {
//        return (int) ((Utils.getDisplayDimention(recyclerView.getContext()).x / 2)
//                - Utils.pxFromDP(30, recyclerView.getContext()));
////        return   ((Utils.getDisplayDimention(recyclerView.getContext()).x/2)
////                -calendarItems.get(day).getView().getWidth()/2);
//
//    }
//
//    public void selectDate(int year, int month) {
//        selectDate(year, month, persianCalendar.getPersianDay());
//    }
//
//    public void setGrid() {
//        horrizental = false;
//    }
//
//    public void requestWeek(int weekNum) {
//        if (weekNum < 1 || weekNum > 6)
//            return;
//
//        horrizental = false;
//        weekly = true;
//        weekNumber = weekNum;
//    }
//
//    public void requestWeek1(int weekNum) {
//        if (weekNum < 1 || weekNum > 30)
//            return;
//
//        //  horrizental = false;
//        // weekly = true;
//        weekNumber = weekNum;
//    }
//
//    public void setGotoWeekListener(GotoWeekListener gotoWeekListener) {
//        this.gotoWeekListener = gotoWeekListener;
//    }
//
//    /**
//     * calculates the number of days in the week
//     *
//     * @param weekNumber
//     * @param calendar
//     * @return
//     */
//    public static int dayNumberInWeek(int weekNumber, PersianCalendar calendar) {
//        // TODO: 8/15/2017 make it reusable in fillCalendarItems()
//        int currentWeek = 1;
//        int numberofDays = 0;
//        PersianCalendar tempCalendar = new PersianCalendar();
//        tempCalendar.setPersianDate(calendar.getPersianYear(), calendar.getPersianMonth(), 1);
//
//        do {
//            if (weekNumber == currentWeek)
//                numberofDays++;
//            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
//                currentWeek++;
//            if (currentWeek > weekNumber)
//                break;
//            tempCalendar.addPersianDate(Calendar.DATE, 1);
//        } while (tempCalendar.getPersianMonth() == calendar.getPersianMonth());
//        return numberofDays;
//
//    }
//
//    /**
//     * calculates the number of days in the week
//     *
//     * @param calendar
//     * @return
//     */
//    public static int weekNumberInMonth(PersianCalendar calendar) {
//        // TODO: 8/15/2017 make it reusable in fillCalendarItems()
//        int currentWeek = 1;
//        PersianCalendar tempCalendar = new PersianCalendar();
//        tempCalendar.setPersianDate(calendar.getPersianYear(), calendar.getPersianMonth(), 1);
//
//        do {
//            if (tempCalendar.getPersianDay() == calendar.getPersianDay())
//                break;
//            if (tempCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
//                currentWeek++;
//
//            tempCalendar.addPersianDate(Calendar.DATE, 1);
//        } while (tempCalendar.getPersianMonth() == calendar.getPersianMonth());
//        return currentWeek;
//
//    }
//
//
//    public interface GotoWeekListener {
//        void gotoWeek(PersianCalendar persianCalendar);
//    }
//
//}
