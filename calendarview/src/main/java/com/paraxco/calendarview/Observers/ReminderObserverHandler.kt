package com.paraxco.calendarview.Helpers.Observers

import com.paraxco.calendarview.Model.CalendarModels.ReminderData
import com.paraxco.commontools.ObserverBase.ObserverHandlerBase
import java.util.*

/**
 * Created by Amin on 10/14/2017.
 */

class ReminderObserverHandler private constructor() : ObserverHandlerBase<ReminderObserverHandler.ReminderObserver>() {
    override fun informObserverInternal(observe: ReminderObserver?, data: MutableList<Any>?) {
        observe?.observeReminderChange((data!! as List<ReminderData>))
    }

    public fun informObservers(data: LinkedList<ReminderData>) {
        super.informObserverListInternal(data as List<Any>?)
    }

    public fun informObservers(data: ReminderData) {
        val tempData = List(1, { data })
        super.informObserverListInternal(tempData)//todo test it
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
