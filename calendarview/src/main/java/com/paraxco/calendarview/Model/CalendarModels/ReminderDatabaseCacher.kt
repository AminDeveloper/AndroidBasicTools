package com.paraxco.calendarview.Model.CalendarModels

import com.activeandroid.query.Select
import com.paraxco.calendarview.Helpers.Observers.ReminderObserverHandler

/**
 * Created by Amin on 10/29/2017.
 */

class ReminderDatabaseCacher : ReminderObserverHandler.ReminderObserver {

    var cachedReminders: List<ReminderData>? = null
        get() {
            if (field == null) {
                ReminderObserverHandler.instance.addObserver(this)
                cachedReminders = Select()
                        .from(ReminderData::class.java)
                        .execute()
            }
            return field
        }
        private set(value) {
            field = value
        }
    override fun observeReminderChange(data: List<ReminderData>?) {
        cancel()
    }

    fun cancel() {
        cachedReminders = null
        ReminderObserverHandler.instance.removeObserver(this)
    }
}
