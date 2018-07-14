package ir.mo.amin.hosseini.calendarview.Helpers.Observers

import ir.mo.amin.hosseini.commontools.ObserverBase.ObserverHandlerBase
import ir.hamsaa.persiandatepicker.util.PersianCalendar

/**
 * Created by Amin on 10/21/2017.
 */

class ChangeDateObserverHandler private constructor() : ObserverHandlerBase<ChangeDateObserverHandler.ChangeDateObserver>() {
    override fun informObserverInternal(observe: ChangeDateObserver?, data: MutableList<Any>?) {
        observe?.observeDateChange(data!![0] as PersianCalendar)
    }

    fun informObservers(persianCalendar:PersianCalendar){
        super.informObserverListInternal(persianCalendar)
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



