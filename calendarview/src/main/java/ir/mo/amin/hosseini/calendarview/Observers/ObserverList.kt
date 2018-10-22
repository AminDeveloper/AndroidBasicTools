package ir.mo.amin.hosseini.calendarview.Observers

import ir.mo.amin.hosseini.calendarview.Helpers.Observers.ChangeDateObserverHandler
import ir.mo.amin.hosseini.calendarview.Helpers.Observers.ReminderObserverHandler
import ir.mo.amin.hosseini.commontools.ObserverBase.ObserverList

/**
 * Created by Amin on 11/7/2017.
 */
class ObserverList : ObserverList() {
    companion object {
        fun getChangeDataObserver() = ChangeDateObserverHandler.instance
        fun getReminderObserver() = ReminderObserverHandler.instance
    }
}