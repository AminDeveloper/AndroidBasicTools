package com.paraxco.calendarview.Helpers.Observers

import com.paraxco.calendarview.Model.CalendarModels.ReminderData

/**
 * Created by Amin on 10/14/2017.
 */

class ReminderObserverHandler private constructor() : ObserverHandlerBase<ReminderObserverHandler.ReminderObserver>() {
    override fun informObserver(observe: ReminderObserver, data: Array<out Any>) {
        observe.observeReminderChange((data[0] as List<ReminderData>))
    }

    fun informObservers(data: List<ReminderData>) {
        super.informObservers(data)
    }

    fun informObservers(data: ReminderData) {
        val tempData = List(1, { data })
        super.informObservers(tempData)
    }

    private object Holder {
        val INSTANCE = ReminderObserverHandler()
    }

    companion object {
        val instance: ReminderObserverHandler by lazy { Holder.INSTANCE }
    }

    interface ReminderObserver {
        fun observeReminderChange(data: List<ReminderData>?)
    }
}
