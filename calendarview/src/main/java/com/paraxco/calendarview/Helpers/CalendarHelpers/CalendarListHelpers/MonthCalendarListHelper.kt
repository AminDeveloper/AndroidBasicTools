package com.paraxco.calendarview.Helpers.CalendarHelpers.CalendarListHelpers

import com.paraxco.calendarview.Fragments.CalendarFragments.CalendarPages.CalendarDateFragment
import com.paraxco.calendarview.Model.CalendarModels.CalendarDayItem
import com.paraxco.commontools.Utils.SmartLogger
import com.paraxco.listtools.ListTools.Adapter.RecyclerView.RecyclerViewDataItemAdapter
import com.paraxco.listtools.ListTools.DataItem.DataItemBase
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import java.util.*
import android.support.v7.widget.RecyclerView



/**
 *
 */

class MonthCalendarListHelper(persianCalendar: PersianCalendar, calendarDateFragment: CalendarDateFragment, layout: Int)
    : CalendarListHelperBase(persianCalendar, calendarDateFragment, layout) {

    override fun refreshRemindersList() {
        //no Reminder list so no refresh needed
    }

    companion object {
        var mSharedPool = RecyclerView.RecycledViewPool()
    }

    override fun getAdapter(): RecyclerViewDataItemAdapter<out DataItemBase<*>>? {
//        val cc = myclass()
//        val rr = myclass2(calendarItems!!, recyclerView!!.context)

//        val mAdapter = MonthCustomAdapter(calendarItems!!, recyclerView!!.context)
        SmartLogger.logDebug(calendarItems!!.size.toString())
        recyclerView!!.recycledViewPool=mSharedPool;
        return RecyclerViewDataItemAdapter.initializeGridRecyclerView(recyclerView, 7, calendarItems)

//        // use this setting to improve performance if you know that changes
//        // in content.xml do not change the layout size of the RecyclerView
//        recyclerView!!.setHasFixedSize(true)
//
//        // use a linear layout manager
//        val mLayoutManager = GridLayoutManager(recyclerView!!.getContext(), 7)
//        recyclerView!!.setLayoutManager(mLayoutManager)
//
//        // specify an adapter (see also next example)
//        val mAdapter = myclass2(calendarItems!!, recyclerView!!.context)
//        recyclerView!!.adapter=mAdapter
//        mAdapter.notifyDataSetChanged()
//        return mAdapter

    }

    override fun fillCalendarItems() {
        val startTime = System.currentTimeMillis()
        calendarItems = LinkedList()
        val tempCalendar = PersianCalendar()
        tempCalendar.setPersianDate(date.persianYear, date.persianMonth, 1)
        do {
            val currentDay = CalendarDayItem(tempCalendar, calendarDateFragment!!, this, layout)
            calendarItems!!.add(currentDay)
            tempCalendar.addPersianDate(Calendar.DATE, 1)
        } while (tempCalendar.persianMonth == date.persianMonth)
        val endtTime = System.currentTimeMillis()
        SmartLogger.logDebug("took " + (endtTime - startTime) + " millisecond")
    }


}
