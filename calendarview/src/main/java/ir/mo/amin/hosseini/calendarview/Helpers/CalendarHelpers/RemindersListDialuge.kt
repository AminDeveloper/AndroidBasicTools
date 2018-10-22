package ir.mo.amin.hosseini.calendarview.Helpers.CalendarHelpers

import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import ir.mo.amin.hosseini.calendarview.Fragments.CalendarFragments.CalendarPages.CalendarDateFragment
import ir.mo.amin.hosseini.calendarview.Helpers.Observers.ReminderObserverHandler
import ir.mo.amin.hosseini.calendarview.Model.CalendarModels.CalendarReminderItem
import ir.mo.amin.hosseini.calendarview.Model.CalendarModels.ReminderData
import ir.mo.amin.hosseini.calendarview.R
import ir.mo.amin.hosseini.commontools.Utils.Utils
import ir.mo.amin.hosseini.listtools.ListTools.Adapter.RecyclerView.RecyclerViewDataItemAdapter
import ir.mo.amin.hosseini.listtools.ListTools.DataItem.DataItemBase
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import kotlinx.android.synthetic.main.calendar_dialog.view.*
import java.util.*

/**
 * Created by Amin on 10/11/2017.
 */

class RemindersListDialuge(val date: Long, val context: Context, val calendarDateFragment: CalendarDateFragment) : ReminderObserverHandler.ReminderObserver {
    private var alertDialog: AlertDialog? = null
    private lateinit var viewadd: View
    private val calendarReminderItems = mutableListOf<CalendarReminderItem>()
    private var listReminder: ArrayList<ReminderData>? = null
    private var reminderAdapter: RecyclerViewDataItemAdapter<out DataItemBase<*>>? = null
    private var hourly: Boolean = false

    private lateinit var reminderRecyclerview: RecyclerView

    private var hourText: String? = null

    constructor(date: Long, context: Context, calendarDateFragment: CalendarDateFragment, hourText: String) : this(date, context, calendarDateFragment) {
        this.hourText = hourText
    }

    init {

    }

    private var showDate: TextView? = null

    private fun init() {
        val mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        viewadd = mInflater.inflate(R.layout.reminder_list_dialug, null, false)
        initializeDialog()
        showDate = viewadd.show_date
        showDate?.setText(Utils.localeNumber(PersianCalendar(date).getPersianLongDate()))

        //initializing view
        reminderRecyclerview = viewadd.findViewById(R.id.reminder_list)
        iniitializeReminders()
        ReminderObserverHandler.instance.addObserver(this)

    }

    private var needsRefresh: Boolean = false

    private fun initializeDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setView(viewadd)
        alertDialog = builder.create()
        alertDialog!!.setCancelable(true)
//        alertDialog!!.setOnShowListener(DialogInterface.OnShowListener {
////            if (needsRefresh) {
////                alertDialog!!.dismiss()
////                showDialog()
////            }
//        })
        alertDialog!!.setOnCancelListener({
            ReminderObserverHandler.instance.removeObserver(this)
        })
        alertDialog!!.setOnDismissListener({
            ReminderObserverHandler.instance.removeObserver(this)
        })
    }


    private fun iniitializeReminders() {
        calendarReminderItems.clear()
        if (listReminder!!.size > 0) {
            listReminder?.mapTo(calendarReminderItems) { CalendarReminderItem(it, calendarDateFragment) }
//            (0 until listReminder!!.size).mapTo(calendarReminderItems) { CalendarReminderItem(listReminder!![it], calendarDateFragment) }
            reminderAdapter = RecyclerViewDataItemAdapter.initializeLinearRecyclerView(reminderRecyclerview, calendarReminderItems)
        }
    }


    private fun getReminders() {
        listReminder = if (hourly)
            ReminderData.getAllForHour(date, hourText)
        else
            ReminderData.getAllForDay(date)

    }

    public fun refreshDialug() {
        if (alertDialog != null && alertDialog!!.isShowing) {
            alertDialog?.dismiss()
            showDialog()
        }
    }

    override fun observeReminderChange(data: List<ReminderData>?) {
        getReminders()
        if (listReminder!!.size > 0)
            iniitializeReminders()
        else
            if (alertDialog != null && alertDialog!!.isShowing)
                alertDialog?.dismiss()
    }

    fun showDialog() {

        getReminders()

        if (listReminder!!.size > 1)
            init()

        if (alertDialog != null)
            alertDialog?.show()
        else {
            if (listReminder!!.size == 1) {
                CalendarDialogReminder(context, calendarDateFragment, listReminder!![0]).showDialog()
            }
            if (listReminder!!.size == 0) {
                CalendarDialogReminder(context, calendarDateFragment, PersianCalendar(date), hourText).showDialog()
            }
        }
    }

    fun requestHour(): RemindersListDialuge {
        hourly = true
        return this
    }
}
