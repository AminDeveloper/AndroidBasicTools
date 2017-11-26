package com.paraxco.calendarview.Fragments

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.FragmentManager
import android.view.View
import com.paraxco.calendarview.Helpers.CalendarHelpers.CalendarViewManager
import com.paraxco.calendarview.R
import com.paraxco.commontools.Utils.SmartLogger
import com.paraxco.listtools.ListTools.ViewGroupSwitcher.ViewContainerFragment
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * Created by Amin on 10/14/2017.
 */
class CalendarFragment : ViewContainerFragment() {
    companion object {
        var calendarViewManager: CalendarViewManager? = null
        var calendarFragment: CalendarFragment? = null
    }

    init {
    }

    var requestedViewType: Int = 0
        set(value) {
            field = value
            calendarViewManager?.selectPage(value)
            SmartLogger.logDebug("value: " + value.toString())
            SmartLogger.logDebug("requestedViewType: " + requestedViewType)
        }

//    fun setRequestedViewTypeValue(value: Int) {
//
//        calendarViewManager?.selectPage(value)
//        requestedViewType=value
//        SmartLogger.logDebug("value: " + value.toString())
//        SmartLogger.logDebug("requestedViewType: " + requestedViewType)
//    }

    var requestedDate: PersianCalendar = PersianCalendar()
        set(value) {
            field = value
            calendarViewManager?.curentDate = value
            SmartLogger.logDebug()
        }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CalendarFragment.calendarFragment = this

        SmartLogger.logDebug()
//        initializeORM()
        doAsync {
            Thread.sleep(350)
            uiThread {
                initializeWithViewManager()
            }
        }

        val endtTime = System.currentTimeMillis()
//        SmartLogger.logDebug("took " + (endtTime - startTime) + " millisecond")
    }

    public override fun getViewRes(): Int {
        return R.layout.calendar_main_fragment
    }

//    private var startTime: Long = 0
//    override fun onStart() {
//        super.onStart()
//        startTime = System.currentTimeMillis()
//        SmartLogger.logDebug("took nothing")
//
//    }

//    override fun onResume() {
//        super.onResume()
//
//    }


    private fun initializeWithViewManager() {
        val startTime = System.currentTimeMillis()

        SmartLogger.logDebug("requestedViewType" + requestedViewType)
//        fab

//        calendarViewManager = CalendarViewManager(childFragmentManager, view!!.findViewById(R.id.insert_point),view!!.findViewById(R.id.fab), view!!)
        calendarViewManager = CalendarViewManager(supportFragmentManager, view!!.findViewById(R.id.insert_point), view!!.findViewById(R.id.fab), view!!)

        calendarViewManager?.curentDate = requestedDate
        SmartLogger.logDebug(requestedViewType.toString())
        calendarViewManager?.initialPageNumber = requestedViewType
        calendarViewManager!!.viewPagerInit()

        val endtTime = System.currentTimeMillis()
        SmartLogger.logDebug("took " + (endtTime - startTime) + " millisecond")
    }

    public var supportFragmentManager: FragmentManager? = null
        get() {
            return if (isAttached)
                childFragmentManager
            else {
                field
            }
        }


//    private fun getSupportFragmentManager(): FragmentManager? {
//
//    }

    override fun onDestroy() {
        super.onDestroy()
        SmartLogger.logDebug()
        calendarViewManager = null

    }

    override fun onDestroyView() {
        super.onDestroyView()
        //todo clear synthetic cache
        SmartLogger.logDebug()

    }

    private val DATA_BASE_NAME = "calendarViewDB"

//    fun initializeORM() {
//        val dbConfiguration = Configuration.Builder(context)
//                .setDatabaseName(DATA_BASE_NAME)
//                //                .setDatabaseVersion(3)
//                .setModelClasses(ReminderData::class.java).create()
//        ActiveAndroid.initialize(dbConfiguration)
//    }
}