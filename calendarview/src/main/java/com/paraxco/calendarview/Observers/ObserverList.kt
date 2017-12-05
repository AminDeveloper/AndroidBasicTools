package com.paraxco.calendarview.Observers

import com.paraxco.calendarview.Helpers.Observers.ChangeDateObserverHandler
import com.paraxco.calendarview.Helpers.Observers.ReminderObserverHandler
import com.paraxco.commontools.ObserverBase.ObserverList

/**
 * Created by Amin on 11/7/2017.
 */
class ObserverList : ObserverList() {
    companion object {
        fun getChangeDataObserver() = ChangeDateObserverHandler.instance
        fun getReminderObserver() = ReminderObserverHandler.instance
    }
}