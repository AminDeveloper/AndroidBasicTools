package com.paraxco.calendarview.Model.CalendarModels


import android.app.Activity
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TextView
import com.paraxco.calendarview.Fragments.CalendarFragments.CalendarPages.CalendarDateFragment
import com.paraxco.calendarview.Helpers.CalendarHelpers.RemindersListDialuge
import com.paraxco.calendarview.Helpers.Observers.ChangeDateObserverHandler
import com.paraxco.calendarview.Helpers.Observers.ReminderObserverHandler
import com.paraxco.calendarview.Interface.ValueContainer
import com.paraxco.calendarview.Observers.ObserverList
import com.paraxco.calendarview.R
import com.paraxco.commontools.Utils.SmartLogger
import com.paraxco.commontools.Utils.Utils
import com.paraxco.listtools.ListTools.DataItem.DataItemBase
import com.paraxco.listtools.ListTools.Interface.ListItemClickListener
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

/**
 *
 */

class CalendarDayItem
//    public CalendarDayItem() {
//        super(R.layout.calendar_item);
//        blankDay = true;//this day is not real day but just for providing a space befor month starting
//    }

(thisDay: PersianCalendar, private val calendarDateFragment: CalendarDateFragment, internal var ValueContainer: ValueContainer<Int>, layout: Int) : DataItemBase<Any>(layout), ChangeDateObserverHandler.ChangeDateObserver, ReminderObserverHandler.ReminderObserver {


    private var reminderCount: Int = 0
    private val blankDay = false
    internal var day: Int = 0
    internal var dayOfWeek: String
    private var dayOfWeekText: TextView? = null
    private var dayText: TextView? = null
    private var circleBackground: ImageView? = null
    var thisDay: PersianCalendar
        internal set
    var isLink = false
        get;
    internal var linkClickListener: View.OnClickListener? = null
    //    int layout;
    internal var main_view_item: RelativeLayout? = null
    internal var showDay: TextView? = null
    internal var spinner: Spinner? = null
    private var reminderText: TextView? = null
    private var reminderLayout: RelativeLayout? = null
    private val millies: Long

    val theyOfWeek: Int
        get() = thisDay.get(Calendar.DAY_OF_WEEK)

    init {
        //        this.layout = layout;
        this.thisDay = PersianCalendar(thisDay.timeInMillis)
        this.day = thisDay.persianDay
        this.dayOfWeek = thisDay.persianWeekDayName
        millies = thisDay.timeInMillis

        doAsyncReminderRefresh()

        clickListener = ListItemClickListener<Any> { ValueContainer.value = day }

//        clickListener = object : ClickableHolder.ListItemClickListener<Any> {
//            override fun onSelected(dataItem: Any) {
//                ValueContainer.value = day
//            }
//        }
    }

    private fun doAsyncReminderRefresh() {
        doAsync {
            reminderCount = ReminderData.getAllForDay(millies).size
            uiThread {
                manageReminders()

            }
        }
    }

    private var isSelected: Boolean = false

    override fun initializeView(view: View) {
        val startTime = System.nanoTime()
        if (globalMilli == 0L)
            globalMilli = System.currentTimeMillis()

        if (blankDay) {
            view.visibility = View.INVISIBLE
            return
        } else {
            view.visibility = View.VISIBLE
        }

        view.setOnLongClickListener {
            //            CalendarDialogReminder(view.context, calendarDateFragment, thisDay).showDialog()
            RemindersListDialuge(thisDay!!.timeInMillis, view.context, calendarDateFragment).showDialog()
            false
        }
//        view.setOnLongClickListener {
//
//        }
        if (isLink)
            view.setOnClickListener(linkClickListener)

//        reminderText = view.reminder_count
//        reminderLayout = view.reminder_layout
//        circleBackground = view.circle_background
//        dayOfWeekText = view.day_Of_week_text
//        dayText = view.day_text
        reminderText = findView(R.id.reminder_count) as TextView?
        reminderLayout = findView(R.id.reminder_layout) as RelativeLayout?
        circleBackground = findView(R.id.circle_background) as ImageView?
        dayOfWeekText = findView(R.id.day_Of_week_text) as TextView?
        dayText = findView(R.id.day_text) as TextView?

        manageReminders()

        dayText!!.text = Utils.localeNumber(day.toString())
        dayOfWeekText!!.text = dayOfWeek

        if (layoutResourceID != R.layout.calendar_month_item) {

            if (shouldBeSelected()) {
                isSelected = true
                circleBackground!!.visibility = View.VISIBLE
                dayText!!.setTextColor(Color.WHITE)
                //      Log.e("ddddddd",thisDay.getPersianDay()+"");
                realDate.timeInMillis = thisDay.timeInMillis
            } else {
                isSelected = false

                circleBackground!!.visibility = View.GONE
                dayText!!.setTextColor(Color.GRAY)

            }
            if (isHolyday(thisDay))

                dayText!!.setTextColor(Color.RED)

            if (isLink)
                dayText!!.setTextColor(Color.LTGRAY)


        } else {
            main_view_item = findView(R.id.view_calendar) as RelativeLayout?  //just month has it


            isSelected = if (shouldBeSelected()) {
                main_view_item?.setBackgroundColor(Color.parseColor("#c0f9f3"))
                //                setDateChangeListener()
                true
            } else {
                main_view_item?.setBackgroundResource(R.color.white)
                false
            }


            if (isHolyday(thisDay)) {
                dayText!!.setTextColor(Color.RED)
                main_view_item?.setBackgroundResource(R.color.friday_calendar)
                if (ValueContainer.value == day && !isLink) {
                    main_view_item?.setBackgroundColor(Color.parseColor("#c0f9f3"))
                }

            } else {
                dayText!!.setTextColor(view.resources.getColor(R.color.text_calendar_months))
            }

            if (isLink) {
                dayText!!.setTextColor(Color.LTGRAY)
                main_view_item?.setBackgroundResource(R.color.light_gray_clandar)
            }
        }
        SmartLogger.logDebug("took nothing" + initializeCount++ + "for" + thisDay.persianMonth)
        val endtTime = System.nanoTime()
        val endtTimeMili = System.currentTimeMillis()

        SmartLogger.logDebug("took " + (endtTime - startTime) + " millisecond and globally " + (endtTimeMili - globalMilli))
    }

    private fun shouldBeSelected(): Boolean = ValueContainer.value == day && !isLink


    private fun setDateChangeListener() {
//        ObserverList.getChangeDataObserver().addObserver(object:ObserverHandlerBase.Observer{
//            override fun observeReminderChange() {
//                initializeView(view)
//                ObserverList.getChangeDataObserver().removeObserver(this)
//            }
//        })
        ObserverList.getChangeDataObserver().addObserver(this)
        ObserverList.getReminderObserver().addObserver(this)


    }

    override fun observeReminderChange(data: List<ReminderData>?) {
        if (PersianCalendar(data!![0].date).persianDay == thisDay.persianDay)
            doAsyncReminderRefresh()
    }

    override fun observeDateChange(data: PersianCalendar?) {
        if (isSelected != shouldBeSelected())
            initializeView(view)
    }

    private fun removeDateChangeObserver() {
        ObserverList.getChangeDataObserver().removeObserver(this)
        ObserverList.getReminderObserver().removeObserver(this)

    }

    override fun onHideItem() {
        super.onHideItem()
        removeDateChangeObserver()
        showingItemCount--
    }

    override fun onShowItem() {
        super.onShowItem()
        setDateChangeListener()
        showingItemCount++
        SmartLogger.logDebug(showingItemCount.toString())

    }

    private fun manageReminders() {

        if (reminderText != null) {
            //            ArrayList<ReminderData> listReminder = ReminderData.getAllForDay(millies);
            //            if(listReminder.size()>0)
            if (reminderCount > 0) {
                reminderLayout!!.visibility = View.VISIBLE

                reminderText!!.text = Integer.toString(reminderCount)
                println("manageReminders day $day $reminderCount")
            } else {
                reminderLayout!!.visibility = View.GONE
                println("manageReminders  gone$day ")

            }
        }

    }

    fun setLink(clickListener: View.OnClickListener) {
        this.linkClickListener = clickListener
        isLink = true
    }

    companion object {
        var showingItemCount = 0

        var initializeCount = 0
        var globalMilli: Long = 0L

        var flag: Boolean? = true
        var realDate = PersianCalendar()

        fun getViewItem(layoutw: Int, c: Activity): View {
            return c.layoutInflater.inflate(layoutw, null, false)

        }

        protected fun isHolyday(thisDay: PersianCalendar): Boolean {
            return thisDay.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
        }
    }

    fun compareDateTo(date: PersianCalendar): Boolean {
        with(thisDay) {
            if (persianDay == date.persianDay &&
                    persianMonth == date.persianMonth &&
                    persianYear == date.persianYear)
                return true
        }
        return false
    }


}
