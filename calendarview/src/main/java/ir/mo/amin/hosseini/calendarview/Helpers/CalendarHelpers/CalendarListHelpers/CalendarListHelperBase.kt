package ir.mo.amin.hosseini.calendarview.Helpers.CalendarHelpers.CalendarListHelpers

import android.opengl.Visibility
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import ir.mo.amin.hosseini.calendarview.Fragments.CalendarFragments.CalendarPages.CalendarDateFragment
import ir.mo.amin.hosseini.calendarview.Helpers.Observers.ChangeDateObserverHandler
import ir.mo.amin.hosseini.calendarview.Helpers.Observers.ReminderObserverHandler
import ir.mo.amin.hosseini.calendarview.Interface.ValueContainer
import ir.mo.amin.hosseini.calendarview.Model.CalendarModels.CalendarDayItem
import ir.mo.amin.hosseini.calendarview.Model.CalendarModels.ReminderData
import ir.mo.amin.hosseini.commontools.Utils.SmartLogger
import ir.mo.amin.hosseini.commontools.Utils.Utils
import ir.mo.amin.hosseini.listtools.ListTools.Adapter.RecyclerView.RecyclerViewDataItemAdapter
import ir.mo.amin.hosseini.listtools.ListTools.DataItem.DataItemBase
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import java.util.*

/**
 * Created by Amin on 10/15/2017.
 */
abstract class CalendarListHelperBase(persianCalendar: PersianCalendar, calendarDateFragment: CalendarDateFragment, internal var layout: Int) : ValueContainer<Int>, ReminderObserverHandler.ReminderObserver, ChangeDateObserverHandler.ChangeDateObserver {


    var date: PersianCalendar
        internal set
    internal var dayItemsAdapter: RecyclerViewDataItemAdapter<out DataItemBase<*>>? = null
    internal var recyclerView: RecyclerView? = null
    internal var calendarItems: LinkedList<CalendarDayItem>? = null
    internal var dateChangeListener: ValueContainer<PersianCalendar>? = null
    internal var gotoWeekListener: GotoWeekListener? = null
    internal var calendarDateFragment: CalendarDateFragment? = calendarDateFragment

    //    public CalendarListHelper(int year, int month, int day) {
    //        persianCalendar = new PersianCalendar();
    //        persianCalendar.setPersianDate(year, month, day);
    //    }

    init {
        this.date = PersianCalendar(persianCalendar.timeInMillis)
    }
    fun visualizeOnRecyclerView(recyclerView: RecyclerView?) {
        visualizeOnRecyclerView(recyclerView,null)

    }

    fun visualizeOnRecyclerView(recyclerView: RecyclerView?, progressBar: ProgressBar?) {
        this.recyclerView = recyclerView
        fillCalendarItems()
        if (calendarItems!!.size == 0)
            return
        checkFristDayOfWeek()
        checkLastDayOfWeek()

        if(progressBar!=null) {
//            recyclerView!!.visibility = View.GONE

            recyclerView!!.addOnLayoutChangeListener { _, _, p2, p3, p4, p5, p6, _, _ ->
                //                    progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }

        dayItemsAdapter = getAdapter()
        SmartLogger.logDebug("took nothing after getAdapter")
        ReminderObserverHandler.instance.addObserver(this)
        ChangeDateObserverHandler.instance.addObserver(this)
        //        scrollToDay();
        //        if (dateChangeListener != null)
        //            dateChangeListener.setValue(persianCalendar);
    }

//    fun visualizeOnView(linearLayout: LinearLayout?) {
//        fillCalendarItems()
//        if (calendarItems!!.size == 0)
//            return
//        checkFristDayOfWeek()
//        checkLastDayOfWeek()
//        if (linearLayout != null) {
//            MonthsListHelper(calendarItems!!, linearLayout).doAsignments()
//        }
//        SmartLogger.logDebug("took nothing after getAdapter")
//        ReminderObserverHandler.instance.addObserver(this)
//    }

    abstract fun getAdapter(): RecyclerViewDataItemAdapter<out DataItemBase<*>>?

    abstract fun fillCalendarItems()
    /**
     * refresh reminders list
     */
    abstract fun refreshRemindersList()

    override fun observeReminderChange(data: List<ReminderData>?) {
//        date = dateChangeListener!!.value
        refreshRemindersList()//for reminder list
//        dayItemsAdapter!!.notifyDataSetChanged()//for reminder counts    }
    }

    override fun observeDateChange(data: PersianCalendar?) {
        refreshRemindersList()
    }

    fun removeObservers() {
        ReminderObserverHandler.instance.removeObserver(this)
        ChangeDateObserverHandler.instance.removeObserver(this)
    }

    private fun checkFristDayOfWeek() {

        while (calendarItems!!.first.theyOfWeek != Calendar.SATURDAY) {
            val tempCalendar = PersianCalendar(calendarItems!!.first.thisDay.timeInMillis)
            tempCalendar.addPersianDate(Calendar.DATE, -1)
            val currentDay = CalendarDayItem(tempCalendar, calendarDateFragment!!, this, layout)
            currentDay.setLink(View.OnClickListener {
                if (gotoWeekListener != null)
                    gotoWeekListener!!.gotoWeek(tempCalendar)
            })
            calendarItems!!.addFirst(currentDay)
        }

    }

    private fun checkLastDayOfWeek() {
        while (calendarItems!!.last.theyOfWeek != Calendar.FRIDAY) {
            val tempCalendar = PersianCalendar(calendarItems!!.last.thisDay.timeInMillis)
            tempCalendar.addPersianDate(Calendar.DATE, 1)
            val currentDay = CalendarDayItem(tempCalendar, calendarDateFragment!!, this, layout)
            currentDay.setLink(View.OnClickListener {
                if (gotoWeekListener != null)
                    gotoWeekListener!!.gotoWeek(tempCalendar)
            })
            calendarItems!!.add(currentDay)
        }
    }


    fun selectDay(day: Int) {
        val lastDate = PersianCalendar(date.timeInMillis)
        date.setPersianDate(date.persianYear, date.persianMonth, day)
        //        scrollToDay();
        if (dateChangeListener != null)
            dateChangeListener!!.value = date
        //        todayTextView.setText(Utils.localeNumber(persianCalendar.getPersianShortDate()));
//        dayItemsAdapter!!.notifyItemChanged(getIndexOf(date))
//        dayItemsAdapter!!.notifyItemChanged(getIndexOf(lastDate))
        refreshRemindersList()
    }

    private fun getIndexOf(date: PersianCalendar): Int {
        return calendarItems!!.indexOf(calendarItems!!.findLast { it.compareDateTo(date) })
//        return (0 until calendarItems!!.size).firstOrNull { calendarItems!![it].thisDay.persianDay== date.persianDay }
//                ?: -1
    }


    /**
     * if all items is not visual this method can help
     */
    private fun scrollToDay() {
        //        int day = persianCalendar.getPersianDay();
        //        ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(day - 1, getOffset(day));

    }

    private fun scrollToDay(day: Int) {
        (recyclerView!!.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(day - 1, getOffset(day))
    }

    fun setDateChangeListener(dateChangeListener: ValueContainer<PersianCalendar>) {
        //        shareDate=dateChangeListener.getValue();
        this.dateChangeListener = dateChangeListener
    }

    @JvmOverloads
    fun selectDate(year: Int, month: Int, day: Int = date.persianDay) {
        date.setPersianDate(year, month, day)

        visualizeOnRecyclerView(recyclerView)
        scrollToDay()
        dayItemsAdapter!!.notifyDataSetChanged()

    }

    fun selectDate(persianCalendar: PersianCalendar) {
        this.date = PersianCalendar(persianCalendar.timeInMillis)
        if (recyclerView != null) {
            visualizeOnRecyclerView(recyclerView)
            scrollToDay()

            //if no item!
            dayItemsAdapter?.notifyDataSetChanged()
        }
    }

    //    @Override
    //    public Integer getValue() {
    //        return persianCalendar.getPersianDay();
    //    }
    override fun getValue(): Int? {
        return if (dateChangeListener!!.value.persianMonth == date.persianMonth && dateChangeListener!!.value.persianYear == date.persianYear)
            dateChangeListener!!.value.persianDay
        else
            -1
    }

    override fun setValue(value: Int?) {
        selectDay(value!!)
    }

    private fun getOffset(day: Int): Int {
        return (Utils.getDisplayDimention(recyclerView!!.context).x / 2 - Utils.pxFromDP(30f, recyclerView!!.context)).toInt()
        //        return   ((Utils.getDisplayDimention(recyclerView.getContext()).x/2)
        //                -calendarItems.get(day).getView().getWidth()/2);

    }

    fun setGotoWeekListener(gotoWeekListener: GotoWeekListener) {
        this.gotoWeekListener = gotoWeekListener
    }


    interface GotoWeekListener {
        fun gotoWeek(persianCalendar: PersianCalendar)
    }

    companion object {

        /**
         * calculates the number of days in the week
         *
         * @param weekNumber
         * @param calendar
         * @return
         */
        fun dayNumberInWeek(weekNumber: Int, calendar: PersianCalendar): Int {
            // TODO: 8/15/2017 make it reusable in fillCalendarItems()
            var currentWeek = 1
            var numberofDays = 0
            val tempCalendar = PersianCalendar()
            tempCalendar.setPersianDate(calendar.persianYear, calendar.persianMonth, 1)

            do {
                if (weekNumber == currentWeek)
                    numberofDays++
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
                    currentWeek++
                if (currentWeek > weekNumber)
                    break
                tempCalendar.addPersianDate(Calendar.DATE, 1)
            } while (tempCalendar.persianMonth == calendar.persianMonth)
            return numberofDays

        }

        /**
         * calculates the number of days in the week
         *
         * @param calendar
         * @return
         */
        fun weekNumberInMonth(calendar: PersianCalendar): Int {
            // TODO: 8/15/2017 make it reusable in fillCalendarItems()
            var currentWeek = 1
            val tempCalendar = PersianCalendar()
            tempCalendar.setPersianDate(calendar.persianYear, calendar.persianMonth, 1)

            do {
                if (tempCalendar.persianDay == calendar.persianDay)
                    break
                if (tempCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
                    currentWeek++

                tempCalendar.addPersianDate(Calendar.DATE, 1)
            } while (tempCalendar.persianMonth == calendar.persianMonth)
            return currentWeek

        }
    }


}
