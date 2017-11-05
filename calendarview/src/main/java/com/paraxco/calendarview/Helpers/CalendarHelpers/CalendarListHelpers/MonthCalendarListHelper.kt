package com.paraxco.calendarview.Helpers.CalendarHelpers.CalendarListHelpers

import com.paraxco.calendarview.Fragments.CalendarFragments.CalendarPages.CalendarDateFragment
import com.paraxco.calendarview.Model.CalendarModels.CalendarDayItem
import com.paraxco.commontools.Utils.SmartLogger
import com.paraxco.listtools.ListTools.Adapter.RecyclerView.RecyclerViewDataItemAdapter
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import java.util.*

/**
 *
 */

class MonthCalendarListHelper(persianCalendar: PersianCalendar, calendarDateFragment: CalendarDateFragment, layout: Int)
    : CalendarListHelperBase(persianCalendar, calendarDateFragment, layout) {

    override fun refreshRemindersList() {
        //no Reminder list so no refresh needed
    }

    override fun getAdapter(): RecyclerViewDataItemAdapter<*>? {
        return RecyclerViewDataItemAdapter.initializeGridRecyclerView(recyclerView, 7, calendarItems)
    }

    override fun fillCalendarItems() {
        val startTime=System.currentTimeMillis()
        calendarItems = LinkedList()
        val tempCalendar = PersianCalendar()
        tempCalendar.setPersianDate(date.persianYear, date.persianMonth, 1)
        do {
            val currentDay = CalendarDayItem(tempCalendar, calendarDateFragment!!, this, layout)
            calendarItems!!.add(currentDay)
            tempCalendar.addPersianDate(Calendar.DATE, 1)
        } while (tempCalendar.persianMonth == date.persianMonth)
        val endtTime=System.currentTimeMillis()
        SmartLogger.logDebug("took "+(endtTime-startTime)+" millisecond")
    }



}
