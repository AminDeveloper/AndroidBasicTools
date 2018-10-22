package ir.mo.amin.hosseini.calendarview.Adapters.InstantaneousEndlessAdapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import ir.mo.amin.hosseini.calendarview.Model.CalendarModels.CalendarDayItem
import ir.mo.amin.hosseini.calendarview.R
import ir.mo.amin.hosseini.commontools.Utils.SmartLogger
import ir.mo.amin.hosseini.commontools.Utils.Utils
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import kotlinx.android.synthetic.main.calendar_month_item.view.*
import java.util.*

/**
 * Created by Amin on 11/5/2017.
 */
class myclass2(var calendarItems: LinkedList<CalendarDayItem>, val context: Context) : RecyclerView.Adapter<myclass2.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.calendar_month_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        SmartLogger.logDebug(calendarItems.size.toString())
        return calendarItems.size
    }

    private val globalValue = 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val startTime = System.nanoTime()
        if (CalendarDayItem.globalMilli == 0L)
            CalendarDayItem.globalMilli = System.currentTimeMillis()

        holder.setDate(calendarItems[position].thisDay)

        val view = holder.itemView
//        view.setOnLongClickListener {
//            //            CalendarDialogReminder(view.context, calendarDateFragment, thisDay).showDialog()
//            RemindersListDialuge(holder.thisDay!!.timeInMillis, view.context, calendarDateFragment).showDialog()
//            false
//        }
//        view.setOnLongClickListener {
//
//        }
        if (holder.isLink)
            view.setOnClickListener(holder.linkClickListener)


        holder.dayText!!.text = Utils.localeNumber(holder.day.toString())
        holder.dayOfWeekText!!.text = holder.dayOfWeek



        if (globalValue == holder.day && !holder.isLink) {
            holder.main_view_item?.setBackgroundColor(Color.parseColor("#c0f9f3"))
        } else {
            holder.main_view_item?.setBackgroundResource(R.color.white)
        }


        if (isHolyday(holder.thisDay!!)) {
            holder.dayText!!.setTextColor(Color.RED)
            holder.main_view_item?.setBackgroundResource(R.color.friday_calendar)
            if (globalValue == holder.day && !holder.isLink) {
                holder.main_view_item?.setBackgroundColor(Color.parseColor("#c0f9f3"))
            }

        } else {
            holder.dayText!!.setTextColor(context.resources.getColor(R.color.text_calendar_months))
        }

        if (holder.isLink) {
            holder.dayText!!.setTextColor(Color.LTGRAY)
            holder.main_view_item?.setBackgroundResource(R.color.light_gray_clandar)
        }

        SmartLogger.logDebug("took nothing" + CalendarDayItem.initializeCount++ + "for" + holder.thisDay!!.persianMonth)
        val endtTime = System.nanoTime()
        val endtTimeMili = System.currentTimeMillis()

        SmartLogger.logDebug("took " + (endtTime - startTime) + " millisecond and globally " + (endtTimeMili - CalendarDayItem.globalMilli))

    }

    protected fun isHolyday(thisDay: PersianCalendar): Boolean {
        return thisDay.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var reminderCount: Int = 0
        val blankDay = false
        var day: Int = 0
        var dayOfWeek: String = ""
        var dayOfWeekText: TextView? = null
        var dayText: TextView? = null
        var circleBackground: ImageView? = null
        var thisDay: PersianCalendar? = null
            internal set
        var isLink = false
            get;
        var linkClickListener: View.OnClickListener? = null
        //    int layout;
        var main_view_item: FrameLayout? = null
        var showDay: TextView? = null
        var spinner: Spinner? = null
        var reminderText: TextView? = null
        var reminderLayout: FrameLayout? = null
        var millies: Long = 0

        val theyOfWeek: Int
            get() = thisDay!!.get(Calendar.DAY_OF_WEEK)

        init {
//            reminderText = itemView.reminder_count
//            reminderLayout = itemView.reminder_layout
////
//////        manageReminders()
////
//////            circleBackground = itemView.circle_background
//            dayOfWeekText = itemView.day_Of_week_text
//            dayText = itemView.day_text
//            main_view_item = itemView.findViewById(R.id.view_calendar)  //just month has it


        }

        fun setDate(thisDay: PersianCalendar) {
//        this.layout = layout;
            this.thisDay = PersianCalendar(thisDay.timeInMillis)
            this.day = thisDay.persianDay
            this.dayOfWeek = thisDay.persianWeekDayName
            millies = thisDay.timeInMillis

//            doAsync {
//                reminderCount = ReminderData.getAllForDay(millies).size
//                uiThread {
//                    manageReminders()
//
//                }
//            }
//            clickListener = ListItemClickListener<Any> { ValueContainer.value = day }

//        clickListener = object : ClickableHolder.ListItemClickListener<Any> {
//            override fun onSelected(dataItem: Any) {
//                ValueContainer.value = day
//            }
//        }        }
        }
    }
}