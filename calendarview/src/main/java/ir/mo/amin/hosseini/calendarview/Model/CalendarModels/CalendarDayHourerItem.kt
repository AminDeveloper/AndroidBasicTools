package ir.mo.amin.hosseini.calendarview.Model.CalendarModels


import android.view.View
import android.widget.Spinner
import android.widget.TextView
import ir.mo.amin.hosseini.calendarview.Fragments.CalendarFragments.CalendarPages.CalendarDateFragment
import ir.mo.amin.hosseini.calendarview.Helpers.CalendarHelpers.RemindersListDialuge
import ir.mo.amin.hosseini.calendarview.R
import ir.mo.amin.hosseini.commontools.Utils.Utils
import ir.mo.amin.hosseini.listtools.ListTools.DataItem.DataItemBase

import ir.hamsaa.persiandatepicker.util.PersianCalendar
import kotlinx.android.synthetic.main.calendar_day_hour.view.*


class CalendarDayHourerItem(currentHour: PersianCalendar, var hourLineNumber: Int, reminderData: ArrayList<ReminderData>, val calendarDateFragment: CalendarDateFragment) : DataItemBase<Any>(R.layout.calendar_day_hour) {
    var currentHour: PersianCalendar? = null
    var showDay: TextView? = null
    var spinner: Spinner? = null
    var listReminderData: ArrayList<ReminderData>? = null


    override fun initializeView(view: View) {
        view.hour_title.text = hourLineNumber.toString() + ":00"
        view.hour_title.text = Utils.localeNumber(view.hour_title.text as String)


        view.text_layout.setOnClickListener {

            showReminderDialog()
        }

        view.root_layout.setOnClickListener {

            showReminderDialog()


        }
        view.line.setBackgroundResource(R.color.line_calander)


        if (listReminderData != null)
            for (j in 0..listReminderData!!.size - 1) {
                var hour: String = listReminderData!!.get(j).firstHour.substring(0, 2)
                if (hour.equals("٠٠")) {
                    hour = "24"
                }
                if (hour != "--") {
                    if (hour.toInt() == hourLineNumber) {
                        view.line.setBackgroundResource(R.color.colorPrimary)
//                        view.root_layout.setOnClickListener {
//                            CalendarDialogReminder(listReminderData!![j], calendarDateFragment, view.context).showDialog()
//                        }
//                        view.text_layout.setOnClickListener {
//                            CalendarDialogReminder(listReminderData!![j], calendarDateFragment, view.context).showDialog()
//                        }
                    }
                } else {
                    view.line.setBackgroundResource(R.color.line_calander)
                }
            }
    }

    private fun showReminderDialog() {
//        CalendarDialogReminder(view.context, calendarDateFragment, currentHour).showDialog()
        RemindersListDialuge(currentHour!!.timeInMillis, view.context, calendarDateFragment, hourLineNumber.toString()).requestHour().showDialog()

    }

    init {
        this.currentHour = PersianCalendar(currentHour.timeInMillis)
        this.listReminderData = reminderData
    }

}
