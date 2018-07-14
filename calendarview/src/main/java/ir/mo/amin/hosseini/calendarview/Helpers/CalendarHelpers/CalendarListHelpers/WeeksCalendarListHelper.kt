package ir.mo.amin.hosseini.calendarview.Helpers.CalendarHelpers.CalendarListHelpers

import android.support.v7.widget.RecyclerView
import android.view.View
import ir.mo.amin.hosseini.calendarview.Fragments.CalendarFragments.CalendarPages.CalendarDateFragment
import ir.mo.amin.hosseini.calendarview.Model.CalendarModels.CalendarDayItem
import ir.mo.amin.hosseini.calendarview.Model.CalendarModels.CalendarReminderItem
import ir.mo.amin.hosseini.calendarview.Model.CalendarModels.ReminderData
import ir.mo.amin.hosseini.commontools.Utils.SmartLogger
import ir.mo.amin.hosseini.listtools.ListTools.Adapter.RecyclerView.RecyclerViewDataItemAdapter
import ir.mo.amin.hosseini.listtools.ListTools.DataItem.DataItemBase
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import java.util.*

/**
 *
 */

class WeeksCalendarListHelper(persianCalendar: PersianCalendar, calendarDateFragment: CalendarDateFragment, layout: Int) : CalendarListHelperBase(persianCalendar, calendarDateFragment, layout) {
    private var weekNumber: Int = 0
    var recyclerViewEvent: RecyclerView? = null
    private var reminderAdapter: RecyclerViewDataItemAdapter<out DataItemBase<*>>? = null
    var calendarReminderItems = ArrayList<CalendarReminderItem>()

    override fun getAdapter(): RecyclerViewDataItemAdapter<out DataItemBase<*>>? {
//        return null
        return RecyclerViewDataItemAdapter.initializeGridRecyclerView(recyclerView, 7, calendarItems)
//        return DataItemListAdapter.initializeHorrizentalRecyclerView(recyclerView, calendarItems)
    }

    override fun refreshRemindersList() {
        refreshCalendarEvents()
    }

    override fun fillCalendarItems() {
        val startTime = System.currentTimeMillis()

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
        val endtTime = System.currentTimeMillis()
        SmartLogger.logDebug("took " + (endtTime - startTime) + " millisecond")
    }


    private fun refreshCalendarEvents() {

        if (recyclerViewEvent == null)
            return

        calendarReminderItems.clear()
        if (isInThisWeek()) {//if data is not equal too global selected data do not show its reminders
            val listReminder = ReminderData.getAllForDay(dateChangeListener!!.value.timeInMillis)
            if (listReminder.size != 0)
                for (i in listReminder.indices)
                    calendarReminderItems.add(CalendarReminderItem(listReminder[i], calendarDateFragment))
            if (calendarReminderItems.size != 0) {
                reminderAdapter = RecyclerViewDataItemAdapter.initializeLinearRecyclerView(recyclerViewEvent, calendarReminderItems)
                recyclerViewEvent?.visibility = View.VISIBLE
            } else
                recyclerViewEvent?.visibility = View.GONE
        } else
            recyclerViewEvent?.visibility = View.GONE

//        dayItemsAdapter?.notifyDataSetChanged()
    }

    public fun isInThisWeek(): Boolean {
        calendarItems?.forEach {
            if (!it.isLink && it.thisDay.persianDay == dateChangeListener!!.value.persianDay &&  it.thisDay.persianMonth == dateChangeListener!!.value.persianMonth&&  it.thisDay.persianYear == dateChangeListener!!.value.persianYear)
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
