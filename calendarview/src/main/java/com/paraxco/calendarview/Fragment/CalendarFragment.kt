package com.paraxco.calendarview.Fragments

import android.nfc.Tag
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.activeandroid.ActiveAndroid
import com.activeandroid.Configuration
import com.paraxco.calendarview.Helpers.CalendarHelpers.CalendarViewManager
import com.paraxco.calendarview.Model.CalendarModels.ReminderData
import com.paraxco.calendarview.R
import com.paraxco.commontools.Utils.SmartLogger
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlinx.android.synthetic.main.calendar_activity.*


/**
 * Created by Amin on 10/14/2017.
 */
class CalendarFragment : Fragment() {
    companion object {
        var calendarViewManager: CalendarViewManager? = null
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




    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.calendar_activity, container, false)
        SmartLogger.logDebug()
        initializeORM()

        return view
    }

    private var startTime :Long= 0
    override fun onStart() {
        super.onStart()
        startTime = System.currentTimeMillis()
        SmartLogger.logDebug("took nothing")

    }

    override fun onResume() {
        super.onResume()
        doAsync {
            Thread.sleep(350)
            uiThread {
                initializeWithViewManager()
            }
        }

        val endtTime = System.currentTimeMillis()
        SmartLogger.logDebug("took " + (endtTime - startTime) + " millisecond")
    }




    private fun initializeWithViewManager() {
        val startTime = System.currentTimeMillis()

        SmartLogger.logDebug("requestedViewType" + requestedViewType)
        fab

        calendarViewManager = CalendarViewManager(childFragmentManager, insert_point, fab, view!!)
        calendarViewManager?.curentDate = requestedDate
        SmartLogger.logDebug(requestedViewType.toString())
        calendarViewManager?.initialPageNumber = requestedViewType
        calendarViewManager!!.viewPagerInit()

        val endtTime = System.currentTimeMillis()
        SmartLogger.logDebug("took " + (endtTime - startTime) + " millisecond")
    }

    override fun onDestroy() {
        super.onDestroy()
        SmartLogger.logDebug()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //todo clear synthetic cache
        SmartLogger.logDebug()

    }

    private val DATA_BASE_NAME= "calendarViewDB"

    fun initializeORM(){
        val dbConfiguration = Configuration.Builder(context)
                .setDatabaseName(DATA_BASE_NAME)
                //                .setDatabaseVersion(3)
                .setModelClasses(ReminderData::class.java).create()
        ActiveAndroid.initialize(dbConfiguration)
    }
}