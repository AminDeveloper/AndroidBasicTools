package ir.mo.amin.hosseini.calendarview.Helpers.AlarmMagement;

import java.util.Calendar;

public class Alarm {
    private Calendar calendar;
    private long time;
    private int id;

    public Alarm(Calendar calendar) {
        this.calendar = calendar;
        this.id =Math.abs((int) System.currentTimeMillis()) ;
    }
    public Alarm(Calendar calendar,int id) {
        this.calendar = calendar;

        this.id =id;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public long getCalendar() {
        return calendar.getTimeInMillis();
    }

    public long getTime() {
        if (calendar != null)
            time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
        if (System.currentTimeMillis() > time) {
            time = time + (1000 * 60 * 60 * 24);
        }
        return time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
