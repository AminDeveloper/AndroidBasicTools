//package com.paraxco.commontools.Observers
//
//import android.content.Context
//import android.content.IntentFilter
//import android.net.ConnectivityManager
//import android.os.Build
//import com.paraxco.commontools.BroadCastReceiver.NetworkChangeReceiver
//import com.paraxco.commontools.ObserverBase.ObserverHandlerBase
//import com.paraxco.commontools.Utils.Utils
//
///**
// * Created by Amin on 03/12/2017.
// */
//class NetworkObserverHandler private constructor() : ObserverHandlerBase<NetworkObserverHandler.NetworkChangeObserver>()  {
//    private object Holder {
//        val INSTANCE = NetworkObserverHandler()
//    }
//
//    companion object {
//        val instance: NetworkObserverHandler by lazy { Holder.INSTANCE }
//        var networkChangeReceiver = NetworkChangeReceiver()
//
//    }
//
//    override fun informObserverInternal(observe: NetworkChangeObserver?, data: MutableList<Any>?) {
//        observe?.onNetworkStateChange(data!![0] as Boolean)
//    }
//
//    fun informObservers(connected: Boolean) {
//        super.informObserverListInternal(connected)
//    }
//
//    override fun addObserver(observer: NetworkChangeObserver?) {
//        super.addObserver(observer)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            if (observersCount== 0)
//                observer!!.getContext().registerReceiver(networkChangeReceiver,
//                        IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
//        }
//        informObservers(Utils.isNetworkAvailable(observer!!.getContext()))
//    }
//
//    override fun removeObserver(observer: NetworkChangeObserver?) {
//        super.removeObserver(observer)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            if (observersCount== 0)
//                observer!!.getContext().unregisterReceiver(networkChangeReceiver)
//        }
//    }
//
//
//    interface NetworkChangeObserver {
//         fun onNetworkStateChange(connected: Boolean)
//        fun getContext():Context
//
//
//    }
//}