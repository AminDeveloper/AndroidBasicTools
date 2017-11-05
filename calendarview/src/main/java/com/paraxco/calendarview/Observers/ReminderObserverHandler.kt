package com.paraxco.calendarview.Helpers.Observers

/**
 * Created by Amin on 10/14/2017.
 */

class ReminderObserverHandler private constructor(): ObserverHandlerBase() {

//    companion object {
//        lateinit var  instance: ReminderObserverHandler
//
//        fun create(): ReminderObserverHandler = instance ?: ReminderObserverHandler()
////        fun create(): ReminderObserverHandler =  instance?: instance
//    }

    private object Holder { val INSTANCE = ReminderObserverHandler() }

    companion object {
         val instance: ReminderObserverHandler by lazy { Holder.INSTANCE }
    }
}
