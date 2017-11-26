package com.paraxco.calendarview.Helpers.Observers

import ir.hamsaa.persiandatepicker.util.PersianCalendar

/**
 * Created by Amin on 10/21/2017.
 */

class ChangeDateObserverHandler private constructor() : ObserverHandlerBase<ChangeDateObserverHandler.ChangeDateObserver>() {
    override fun informObserver(observe: ChangeDateObserver, data: Array<out Any>) {
        observe.observeDateChange(data[0] as PersianCalendar)
    }
    fun informObservers(persianCalendar:PersianCalendar){
        super.informObservers(persianCalendar)
    }

    private object Holder {
        val INSTANCE = ChangeDateObserverHandler()
    }

    companion object {
        public val instance: ChangeDateObserverHandler by lazy { Holder.INSTANCE }
    }
    interface ChangeDateObserver {
        fun observeDateChange(data:PersianCalendar?)
    }
}



