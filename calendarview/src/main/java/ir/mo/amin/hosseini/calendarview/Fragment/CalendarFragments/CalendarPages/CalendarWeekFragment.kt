package ir.mo.amin.hosseini.calendarview.Fragments.CalendarFragments.CalendarPages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.mo.amin.hosseini.calendarview.Fragments.CalendarFragments.Containers.CalendarWeeksViewFragment
import ir.mo.amin.hosseini.calendarview.Helpers.CalendarHelpers.CalendarListHelpers.CalendarListHelperBase
import ir.mo.amin.hosseini.calendarview.Helpers.CalendarHelpers.CalendarListHelpers.WeeksCalendarListHelper
import ir.mo.amin.hosseini.calendarview.R
import ir.mo.amin.hosseini.commontools.Utils.SmartLogger
import ir.mo.amin.hosseini.commontools.Utils.Utils
import ir.hamsaa.persiandatepicker.util.PersianCalendar

class CalendarWeekFragment : CalendarDateFragment() {


    private var calendarHelper: WeeksCalendarListHelper? = null
    //    private var reminderAdapter: DataItemListAdapter<*>? = null
//    private val calendarReminderItems = mutableListOf<CalendarReminderItem>()
    private var requestedWeek: Int = 1
    private var calendarWeeksViewFragment: CalendarWeeksViewFragment? = null

    init {
        calendarHelper = WeeksCalendarListHelper(PersianCalendar(), this, R.layout.calendar_item)
    }

    //    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = getInflatedView(inflater, container)
//        return view
//    }
//
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
        initializeCalendar()
        iniitializeReminders()
        /*  fab.setOnClickListener {
              CalendarDialogReminder(customView, getData()).showDialog()
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

    private fun iniitializeReminders() {
        calendarHelper!!.refreshRemindersList()//must be initialized
//        calendarReminderItems.clear()
//        val listReminder = ReminderData.getAllForDay(getData()!!.timeInMillis)
//        if (listReminder.size != 0)
//            for (i in 0..listReminder.size - 1)
//                calendarReminderItems.add(CalendarReminderItem(listReminder.get(i), this))
//        reminderAdapter = DataItemListAdapter.initializeLinearRecyclerView(recycle_event, calendarReminderItems)

    }

    private var manualCall: Boolean = true

    private fun initializeCalendar() {
        calendarHelper!!.setEventRecycler(customView?.findViewById(R.id.event_list)!!)
        calendarHelper!!.requestWeek(requestedWeek)
        calendarHelper!!.setDateChangeListener(dateContainer!!)
        calendarHelper!!.visualizeOnRecyclerView(customView?.findViewById(R.id.calendar_list))
        calendarHelper!!.setGotoWeekListener(object : CalendarListHelperBase.GotoWeekListener {
            override fun gotoWeek(persianCalendar: PersianCalendar) {
                dateContainer?.value = persianCalendar
                calendarWeeksViewFragment?.updateFragmentDate(persianCalendar!!)
            }
        })
//        val persianCalendar = PersianCalendar()//today
//        (getViewByID(R.id.today_text_view) as TextView).text = getTodayString(persianCalendar)
//        (getViewByID(R.id.today_text_view) as TextView).setOnClickListener {
//            //  dateContainer!!.value = persianCalendar
//            iniitializeReminders()
//            //   calendarWeeksViewFragment?.updateFragmentDate(persianCalendar)
//
//        }

    }


    private fun getTodayString(persianCalendar: PersianCalendar): String {
        return Utils.localeNumber(getResString(R.string.today) + " " + persianCalendar.persianWeekDayName + " " + persianCalendar.persianDay + " " + persianCalendar.persianMonthName + " " + persianCalendar.persianYear)
    }

    fun getResString(res: Int): String {
        return view!!.context!!.getString(res)
    }

    public fun requestWeek(requestedWeek: Int, calendarWeeksViewFragment: CalendarWeeksViewFragment): CalendarWeekFragment {
        this.requestedWeek = requestedWeek
        this.calendarWeeksViewFragment = calendarWeeksViewFragment
        return this
    }

    public fun setDate(persianCalendar: PersianCalendar): CalendarWeekFragment {
//        if (manualCall)
        calendarHelper!!.selectDate(persianCalendar)

        return this
    }

    public override fun getData(): PersianCalendar? {

        return calendarHelper!!.date
    }

    public fun getCurentWeek(): Int {
        return requestedWeek
    }

    override fun reInitNeeded(): Boolean {
        SmartLogger.logDebug(calendarHelper!!.isInThisWeek().toString())
        return !calendarHelper!!.isInThisWeek()
    }

    override fun onPageShow() {
//        com.activeandroid.util.Log.d(calendarHelper?.date!!)
//        calendarWeeksViewFragment?.setToolbarDate(calendarHelper?.date!!)
    }

    companion object {
        public fun getInflatedView(inflater: LayoutInflater?, container: ViewGroup?): View {
            return inflater!!.inflate(R.layout.calendar_week, container, false)
        }

        public fun getLayoutRes(): Int {
            return R.layout.calendar_week
        }
    }
}
