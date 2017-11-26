package com.paraxco.calendarview.Observers

import com.paraxco.calendarview.Helpers.Observers.ChangeDateObserverHandler
import com.paraxco.calendarview.Helpers.Observers.ReminderObserverHandler

/**
 * Created by Amin on 11/7/2017.
 */
class ObserverList {
    companion object {
        fun getChangeDataObserver() = ChangeDateObserverHandler.instance
        fun getReminderObserver() = ReminderObserverHandler.instance

    }
}