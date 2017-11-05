package com.paraxco.calendarview.Helpers.CalendarHelpers.CalendarListHelpers

import android.support.v7.widget.RecyclerView
import android.view.View
import com.paraxco.calendarview.Fragments.CalendarFragments.CalendarPages.CalendarDateFragment
import com.paraxco.calendarview.Model.CalendarModels.CalendarDayItem
import com.paraxco.calendarview.Model.CalendarModels.CalendarReminderItem
import com.paraxco.calendarview.Model.CalendarModels.ReminderData
import com.paraxco.commontools.Utils.SmartLogger
import com.paraxco.listtools.ListTools.Adapter.RecyclerView.RecyclerViewDataItemAdapter
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import java.util.*

/**
 *
 */

class WeeksCalendarListHelper(persianCalendar: PersianCalendar, calendarDateFragment: CalendarDateFragment, layout: Int) : CalendarListHelperBase(persianCalendar, calendarDateFragment, layout) {
    private var weekNumber: Int = 0
    var recyclerViewEvent: RecyclerView? = null
    private var reminderAdapter: RecyclerViewDataItemAdapter<*>? = null
    var calendarReminderItems = ArrayList<CalendarReminderItem>()

    override fun getAdapter():  RecyclerViewDataItemAdapter<*>? {
        return RecyclerViewDataItemAdapter.initializeGridRecyclerView(recyclerView, 7, calendarItems)
//        return DataItemListAdapter.initializeHorrizentalRecyclerView(recyclerView, calendarItems)
    }

    override fun refreshRemindersList() {
        refreshCalendarEvents()
    }

    override fun fillCalendarItems() {
        val startTime=System.currentTimeMillis()

        calendarItems = LinkedList()
        val tempCalendar = PersianCalendar()
        tempCalendar.setPersianDate(date.persianYear, date.persianMonth, 1)
        var currentWeek = 1
        do {
            val currentDay = CalendarDayItem(tempCalendar, calendarDateFragment!!, this, layout)

            if (weekNumber == currentWeek)
                calendarItems!!.add(currentDay)
            if (currentDay.theyOfWeek == Calendar.FRIDAY)
                currentWeek++
            if (currentWeek > weekNumber)
                break

            tempCalendar.addPersianDate(Calendar.DATE, 1)
        } while (tempCalendar.persianMonth == date.persianMonth)
        val endtTime=System.currentTimeMillis()
        SmartLogger.logDebug("took "+(endtTime-startTime)+" millisecond")
    }


    private fun refreshCalendarEvents() {

        if (recyclerViewEvent == null)
            return

        calendarReminderItems.clear()
        if (isInThisWeek()) {//if data is not equal too global selected data do not show its reminders
            val listReminder = ReminderData.getAllForDay(date.timeInMillis)
            if (listReminder.size != 0)
                for (i in listReminder.indices)
                    calendarReminderItems.add(CalendarReminderItem(listReminder[i], calendarDateFragment))
            reminderAdapter = RecyclerViewDataItemAdapter.initializeLinearRecyclerView(recyclerViewEvent, calendarReminderItems)
            recyclerViewEvent?.visibility = View.VISIBLE

        } else
            recyclerViewEvent?.visibility = View.GONE

//        dayItemsAdapter?.notifyDataSetChanged()
    }

    private fun isInThisWeek(): Boolean {
        calendarItems?.forEach {
            if(!it.isLink && it.thisDay.persianDay == dateChangeListener!!.value.persianDay )
                return true
        }
        return false
    }

    fun requestWeek(weekNum: Int) {
        if (weekNum < 1 || weekNum > 6)
            return

        weekNumber = weekNum
    }

    fun setEventRecycler(recyclerViewEvent: RecyclerView) {
        this.recyclerViewEvent = recyclerViewEvent
    }
}
