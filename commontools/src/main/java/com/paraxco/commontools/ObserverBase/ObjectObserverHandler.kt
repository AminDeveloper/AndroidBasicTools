//package com.paraxco.calendarview.Helpers.Observers
//
///**
// * Created by Amin on 10/14/2017.
// */
//
//class ObjectObserverHandler<T> {
//
//    var observerList: MutableList<ObjectObserver<T>> = mutableListOf()
//    public fun addObserver(observer: ObjectObserver<T>) {
//        observerList.add(observer)
//    }
//
//    public fun removeObserver(observer: ObjectObserver<T>) {
//        observerList.add(observer)
//    }
//
//    public fun informObserverListInternal(data: T) {
//        observerList.forEach { it.observe(data) }
//    }
//
//    interface ObjectObserver<in T> {
//        fun observe(observable: T)
//    }
//
//}
