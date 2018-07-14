package ir.mo.amin.hosseini.calendarview.Model.CalendarModels;


import com.activeandroid.annotation.Column;
import com.activeandroid.query.Select;
import ir.mo.amin.hosseini.calendarview.Helpers.AlarmMagement.Alarm;
import ir.mo.amin.hosseini.calendarview.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class ReminderData extends CustomORMModel {

    @Column()
    private String title;
    @Column()
    private String description;
    @Column()
    private String firstHour;
    @Column()
    private String secondHour;
    @Column()
    private Long date;
    @Column()
    private String remind;
    @Column()
    private boolean enable = true;
    @Column()
    private int alarmID;


    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {

        this.title = name;
    }

    public String getDescription() {
        return description;
    }

    public int setDescription(String description) {
        int res = checkForEmptyMessage(description);
        //if (res < 0)
        this.description = description;
        return res;
    }

    public String getFirstHour() {
        return firstHour;
    }

    public void setFirstHour(String firstHour) {
        this.firstHour = firstHour;
    }

    public String getSecondHour() {
        return secondHour;
    }

    public void setSecondHour(String secondHour) {
        this.secondHour = secondHour;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }


    private int checkForEmptyMessage(String value) {

        if (value.length() <= 0)
            return R.string.empty_field;
        return -1;
    }

    public void remove() {
        delete();
//        new Delete().from(ReminderData.class).where("Name = ?","okay1").execute();
    }

//    public void saveReminder() {
//        save();
//    }
//
//    public void updateReminder() {
////        delete();
//        save();
//    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Calendar getFirstDate() {
        Calendar calendar = new PersianCalendar(date);
        int hour = Integer.parseInt(firstHour.substring(0, 2));
        int minute = Integer.parseInt(firstHour.substring(3, 5));
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);

        return calendar;
    }

    public void setAlarmID(int alarmID) {
        this.alarmID = alarmID;
    }

    public int getAlarmID() {
        return alarmID;
    }


    //    public static ArrayList<ReminderData> getAllForHour(long value) {
//        PersianCalendar persianCalendar = new PersianCalendar(value);
//        PersianCalendar persianCalendarLower = new PersianCalendar();
//        PersianCalendar persianCalendarUpper = new PersianCalendar();
//
//        persianCalendarLower.setPersianDate(persianCalendar.getPersianYear(), persianCalendar.getPersianMonth(), persianCalendar.getPersianDay());
//        persianCalendarLower.set(Calendar.HOUR_OF_DAY, persianCalendar.get(Calendar.HOUR_OF_DAY));
//
//        persianCalendarUpper.setPersianDate(persianCalendar.getPersianYear(), persianCalendar.getPersianMonth(), persianCalendar.getPersianDay());
//        persianCalendarUpper.set(Calendar.HOUR_OF_DAY, persianCalendar.get(Calendar.HOUR_OF_DAY));
//
//        persianCalendarUpper.add(Calendar.HOUR_OF_DAY, 1);
//
//        String lowerBound = String.valueOf(persianCalendarLower.getTimeInMillis()-1);
//        String upperBound = String.valueOf(persianCalendarUpper.getTimeInMillis()+1);
//
//        List<ReminderData> list = new Select()
//                .from(ReminderData.class)
//                .where("firstHour >= ? AND firstHour < ?", lowerBound, upperBound)
//                .execute();
//
//
//        return new ArrayList(list);
//    }


    static ReminderDatabaseCacher reminderDatabaseCacher = new ReminderDatabaseCacher();

    public static List<ReminderData> getAll() {
        return reminderDatabaseCacher.getCachedReminders();
    }

    public static ArrayList<ReminderData> getAllForDay(long value) {
        PersianCalendar date = new PersianCalendar();
        ArrayList<ReminderData> result = new ArrayList<>();
        date.setTimeInMillis(value);
        int year = date.getPersianYear();
        int month = date.getPersianMonth();
        int day = date.getPersianDay();
        date.setPersianDate(year, date.getPersianMonth(), date.getPersianDay());
        List<ReminderData> list = getAll();
        PersianCalendar test = new PersianCalendar();
        for (ReminderData reminderData : list) {
            test.setTimeInMillis(reminderData.getDate());
            int testYear = Integer.valueOf(test.getPersianShortDate().substring(0, 4));
            if (testYear == year && test.getPersianMonth() == month && test.getPersianDay() == day)
                result.add(reminderData);
        }
        return result;
    }

    public static ArrayList<ReminderData> getAllForHour(long value, String hourText) {
        PersianCalendar date = new PersianCalendar();
        ArrayList<ReminderData> result = new ArrayList<>();
        date.setTimeInMillis(value);
        int year = date.getPersianYear();
        int month = date.getPersianMonth();
        int day = date.getPersianDay();
//        int hour = date.get(Calendar.HOUR_OF_DAY);

        date.setPersianDate(year, date.getPersianMonth(), date.getPersianDay());
        List<ReminderData> list = getAll();
        PersianCalendar test = new PersianCalendar();
        for (ReminderData reminderData : list) {
            test.setTimeInMillis(reminderData.getDate());
            int testYear = Integer.valueOf(test.getPersianShortDate().substring(0, 4));
            if (testYear == year && test.getPersianMonth() == month && test.getPersianDay() == day)
                if (checkHours(hourText, reminderData.getFirstHour()))
                    result.add(reminderData);
        }
        return result;
    }

    public static ReminderData getWithAlamrID(Alarm alarm) {
        List<ReminderData> list = new Select()
                .from(ReminderData.class)
                .where("alarmID = ? ", alarm.getId())
                .execute();
        if (list.size() == 0)
            return null;
        return list.get(0);
    }

    public static ReminderData getWithReminderID(String reminderID) {
        List<ReminderData> list = new Select()
                .from(ReminderData.class)
                .where("id = ? ", reminderID)
                .execute();
        return list.get(0);
    }

    private static boolean checkHours(String hourText, String firstHour) {
        boolean result;
        try {
            result = Integer.parseInt(hourText) == Integer.parseInt(firstHour.substring(0, 2));
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }


}
