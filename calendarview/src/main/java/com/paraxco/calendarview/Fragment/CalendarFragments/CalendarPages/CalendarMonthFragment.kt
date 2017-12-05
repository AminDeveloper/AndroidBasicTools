package com.paraxco.calendarview.Fragments.CalendarFragments.CalendarPages

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.paraxco.calendarview.Fragments.CalendarFragments.Containers.CalendarMonthsFragment
import com.paraxco.calendarview.Helpers.CalendarHelpers.CalendarListHelpers.CalendarListHelperBase
import com.paraxco.calendarview.Helpers.CalendarHelpers.CalendarListHelpers.MonthCalendarListHelper
import com.paraxco.calendarview.Model.CalendarModels.CalendarReminderItem
import com.paraxco.calendarview.R
import com.paraxco.commontools.Utils.SmartLogger
import com.paraxco.commontools.Utils.Utils
import com.paraxco.listtools.ListTools.Adapter.RecyclerView.RecyclerViewDataItemAdapter
import ir.hamsaa.persiandatepicker.util.PersianCalendar


class CalendarMonthFragment : CalendarDateFragment() {

//    val persianCalendar = PersianCalendar()
    override fun getData(): PersianCalendar? {
        SmartLogger.logDebug(calendarHelper!!.date.persianDay.toString())
        return calendarHelper!!.date
    }

    var item_reminder: FrameLayout? = null
    private var calendarHelper: MonthCalendarListHelper? = null
    private var reminderAdapter: RecyclerViewDataItemAdapter<*>? = null
    private val calendarReminderItems = mutableListOf<CalendarReminderItem>()
    //   private var requestedMonth: Int = 1
    private var calendarMonthsFragment: CalendarMonthsFragment? = null

    init {
        calendarHelper = MonthCalendarListHelper(PersianCalendar(), this, R.layout.calendar_month_item)
    }

    //    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = getInflatedView(inflater, container)
//        return view
//    }
//
//    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setView(view)
//    }
    override fun getViewRes(): Int {
        return getLayoutRes()
    }

    override fun setView(view: View?) {
        super.setView(view)
        //    iniitializeReminders()
        initializeCalendar()
        /* fab.setOnClickListener {
             CalendarDialogReminder(customView,getData()).showDialog()
         }*/
        SmartLogger.logDebug("took nothing")
    }

    override fun detachView() {
        calendarHelper?.removeObservers()
    }

    override fun onShowingView() {
        super.onShowingView()
        if (view == null) return
    }


    /* private fun iniitializeReminders() {
         calendarReminderItems.clear()
         calendarReminderItems.add(CalendarReminderItem())
         calendarReminderItems.add(CalendarReminderItem())
         reminderAdapter = DataItemListAdapter.initializeLinearRecyclerView(customView?.findViewById(R.id.event_list) as RecyclerView?, calendarReminderItems)
     }
 */


    private fun iniitializeReminders() {

        /* calendarReminderItems.clear()
         val listReminder = ReminderData.getAllForDay(getData()!!.timeInMillis)
         if (listReminder.size != 0)
             for (i in 0..listReminder.size - 1)
                 calendarReminderItems.add(CalendarReminderItem(listReminder.get(i).description, listReminder.get(i).title,CalendarListHelper.shareDate))
         reminderAdapter = DataItemListAdapter.initializeLinearRecyclerView(customView?.findViewById(R.id.event_list) as RecyclerView?, calendarReminderItems)
 */
    }


    private var manualCall: Boolean = true
    private fun initializeCalendar() {

        calendarHelper!!.setDateChangeListener(dateContainer!!)
//        calendarHelper!!.visualizeOnView(customView?.findViewById(R.id.month_list))
        val recyclerView: RecyclerView = customView!!.findViewById(R.id.calendar_list)!!
//        val progressBar: ProgressBar = customView!!.findViewById(R.id.progressBar)!!

        calendarHelper!!.visualizeOnRecyclerView(recyclerView)
        calendarHelper!!.setGotoWeekListener(object : CalendarListHelperBase.GotoWeekListener {
            override fun gotoWeek(persianCalendar: PersianCalendar) {
                calendarMonthsFragment?.updateFragmentDate(persianCalendar!!)
            }

        })

        /* today_text_view.text = getTodayString(persianCalendar)
         today_text_view.setOnClickListener {

             //   calendarHelper!!.selectDate(persianCalendar)
             //  calendarMonthsFragment?.setToolbarDate(calendarHelper?.date!!)
             // calendarMonthsFragment?.updateFragmentDate(persianCalendar)
 //            todo goto correct page
         }
 */

        /* item_reminder= customView?.findViewById(R.id.view_calendar) as FrameLayout
         item_reminder!!.setOnClickListener(View.OnClickListener {
             item_reminder!!.setBackgroundColor(Color.RED)
         })*/
        //    var v1=CalendarDayItem.getViewItem(R.layout.calendar_month_item,activity)
        /*  val inflater = customView!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
          val v1 = inflater.inflate(R.layout.calendar_month_item, null)
          item_reminder= v1?.findViewById(R.id.view_calendar) as FrameLayout
          item_reminder!!.setOnLongClickListener({
              item_reminder!!.setBackgroundColor(Color.RED)
              Log.e("dddddddddddddd","ddddddddddddddd")
          true
          })*/


    }


    fun setDate(persianCalendar: PersianCalendar): CalendarMonthFragment {
        calendarHelper!!.selectDate(persianCalendar)
//        manualCall =true
        return this
    }

    override fun reInitNeeded(): Boolean {
        SmartLogger.logDebug("YEAR "+getData()!!.persianYear.toString() + " ==  " +
                dateContainer!!.value.persianYear.toString() + " " +

              " MONTH "+ getData()!!.persianMonth.toString()+" == "+
                dateContainer!!.value.persianMonth.toString())

                return dateContainer ==null || !(getData()!!.persianYear == dateContainer!!.value.persianYear
                && getData()!!.persianMonth == dateContainer!!.value.persianMonth
                )
    }

    override fun onPageShow() {
    }

    private fun getTodayString(persianCalendar: PersianCalendar): String {
        return Utils.localeNumber(getResString(R.string.today) + " " + persianCalendar.persianWeekDayName + " " + persianCalendar.persianDay + " " + persianCalendar.persianMonthName + " " + persianCalendar.persianYear)
    }

    fun getResString(res: Int): String {
        return view!!.context!!.getString(res)
    }

    companion object {
        public fun getInflatedView(inflater: LayoutInflater?, container: ViewGroup?): View {
            return inflater!!.inflate(R.layout.calendar_fragment, container, false)
        }

        fun getLayoutRes(): Int {
            return R.layout.calendar_fragment
        }
    }
}
