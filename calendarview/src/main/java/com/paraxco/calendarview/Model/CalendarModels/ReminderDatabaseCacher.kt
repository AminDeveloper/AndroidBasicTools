package com.paraxco.calendarview.Model.CalendarModels

import com.activeandroid.query.Select
import com.paraxco.calendarview.Helpers.Observers.ObserverHandlerBase
import com.paraxco.calendarview.Helpers.Observers.ReminderObserverHandler

/**
 * Created by Amin on 10/29/2017.
 */

class ReminderDatabaseCacher : ObserverHandlerBase.Observer {
    var cachedReminders: List<ReminderData>? = null
        get() {
            if (field == null) {
                ReminderObserverHandler.instance.addObserver(this)
                cachedReminders = Select()
                        .from(ReminderData::class.java)
                        .execute()
            }
            return field;
        }
        private set(value) {
            field = value
        }

    override fun observe() {
        cancel()
    }

    fun cancel() {
        cachedReminders = null
        ReminderObserverHandler.instance.removeObserver(this)
    }
}
