package com.paraxco.calendarview.Helpers.Observers

/**
 * Created by Amin on 10/21/2017.
 */

class ChangeDateObserverHandler private constructor() : ObserverHandlerBase() {

    private object Holder {
        val INSTANCE = ChangeDateObserverHandler()
    }

    companion object {
        public val instance: ChangeDateObserverHandler by lazy { Holder.INSTANCE }
    }
}



