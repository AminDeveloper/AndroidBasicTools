package com.paraxco.calendarview.Helpers.AlarmMagement;


import android.content.Context;


public interface AlarmEvent {
    public long onEvent(Context context, Alarm alarm);
}
