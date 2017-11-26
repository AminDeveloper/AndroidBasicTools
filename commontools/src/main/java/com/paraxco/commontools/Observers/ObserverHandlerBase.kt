package com.paraxco.calendarview.Helpers.Observers

import com.paraxco.commontools.Utils.SmartLogger
import java.util.concurrent.ConcurrentHashMap


/**
 * Created by Amin on 10/14/2017.
 */
open abstract class ObserverHandlerBase<OBSERVER_TYPE> {

    var observerList: ConcurrentHashMap<OBSERVER_TYPE,Boolean> = ConcurrentHashMap()
    fun addObserver(observer: OBSERVER_TYPE) {
        synchronized(this, {
            observerList.put(observer,true)
            SmartLogger.logDebug("observerList size:" + observerList.size.toString())
        })
    }

    fun removeObserver(observer: OBSERVER_TYPE) {
        synchronized(this, {
            observerList.remove(observer)
            SmartLogger.logDebug("observerList size:" + observerList.size.toString())
        })
    }

    open protected fun informObservers(vararg data:Any) {
        synchronized(this, {
            SmartLogger.logDebug("observerList size:" + observerList.size.toString())
            observerList.forEach {
//                it.key.observe()
                informObserver(it.key,data)
            }
        })
    }

     abstract fun informObserver(observe: OBSERVER_TYPE, data: Array<out Any>)


    interface Observer<in T> {
        fun observe(data:T?)
    }

}
