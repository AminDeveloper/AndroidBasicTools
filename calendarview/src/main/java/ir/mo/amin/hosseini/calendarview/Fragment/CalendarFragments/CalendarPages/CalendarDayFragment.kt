package ir.mo.amin.hosseini.calendarview.Fragments.CalendarFragments.CalendarPages

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ir.mo.amin.hosseini.calendarview.Model.CalendarModels.CalendarDayHourerItem
import ir.mo.amin.hosseini.calendarview.Model.CalendarModels.ReminderData
import ir.mo.amin.hosseini.calendarview.R
import ir.mo.amin.hosseini.commontools.Utils.SmartLogger
import ir.mo.amin.hosseini.commontools.Utils.Utils
import ir.mo.amin.hosseini.listtools.ListTools.Adapter.RecyclerView.RecyclerViewDataItemAdapter
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import java.util.*


class CalendarDayFragment : CalendarDateFragment() {

    private var currentDay: PersianCalendar = PersianCalendar()

    public override fun getData(): PersianCalendar? {
//       return calendarHelper!!.date
        return currentDay
    }

    //    private var calendarHelper: CalendarListHelper? = null
    private var reminderAdapter: RecyclerViewDataItemAdapter<*>? = null
    private val calendarHourItems = mutableListOf<CalendarDayHourerItem>()
    var showDay: TextView? = null

    init {
        //   currentDay = CalendarListHelper(PersianCalendar(), R.layout.calendar_day_hour)
    }

//    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = getInflatedView(inflater, container)
//        return null
//    }
//
//
//    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
////        setView(view)
//    }


    fun setDate(persianCalendar: PersianCalendar): CalendarDayFragment {
//        if (manualCall)
//        calendarHelper!!.selectDate(persianCalendar)
        currentDay = PersianCalendar(persianCalendar.timeInMillis)
//        manualCall =true
        return this
    }

    //  private var fab: FloatingActionButton? = null
    override fun getViewRes(): Int {
        return getLayoutRes()
    }

    override fun setView(view: View?) {
        super.setView(view)
        iniitializeReminders()

        /* fab = customView?.findViewById(R.id.fab) as FloatingActionButton
         fab!!.setOnClickListener {
          CalendarDialogReminder(customView,currentDay).showDialog()
         }*/
//        show_day.text=getTodayString(calendarHelper?.date!!)
        showDay = customView?.findViewById(R.id.show_day)

        showDay?.text = getTodayString(currentDay)
        if (currentDay.persianWeekDayName == "جمعه")
            showDay?.setTextColor(Color.RED)
        else
            showDay?.setTextColor(getView()!!.resources.getColor(R.color.color_blue_dark))
        SmartLogger.logDebug("took nothing")
    }

    override fun detachView() {

    }

    override fun onShowingView() {
        super.onShowingView()
        if (view == null) return

    }

    /**
     * initializes all hour lines in this day
     */
    fun iniitializeReminders() {
        calendarHourItems.clear()
        val listReminder = ReminderData.getAllForDay(currentDay.timeInMillis)
        var currentHour = PersianCalendar(currentDay.timeInMillis)
        currentHour.set(Calendar.HOUR, 1)
        var i = 1
        while (i < 25) {
            //    Log.e("ssssssssssssss",currentHour.get(Calendar.HOUR).toString()+"")
            calendarHourItems.add(CalendarDayHourerItem(currentHour, i, listReminder, this))
            i++
        }
        /* calendarHourItems.add(CalendarDayHourerItem())
         calendarHourItems.add(CalendarDayHourerItem())
         calendarHourItems.add(CalendarDayHourerItem())
         calendarHourItems.add(CalendarDayHourerItem())
         calendarHourItems.add(CalendarDayHourerItem())
         calendarHourItems.add(CalendarDayHourerItem())
         calendarHourItems.add(CalendarDayHourerItem())
         calendarHourItems.add(CalendarDayHourerItem())
         calendarHourItems.add(CalendarDayHourerItem())
         calendarHourItems.add(CalendarDayHourerItem())*/
        reminderAdapter = RecyclerViewDataItemAdapter.initializeLinearRecyclerView(customView!!.findViewById(R.id.event_list), calendarHourItems)
    }

    private var manualCall: Boolean = true

//    private fun initializeCalendar() {
//
//
//        calendarHelper!!.setDateChangeListener(object : CalendarDayItem.ValueContainer<PersianCalendar> {
//            override fun getValue(): PersianCalendar? {
//                return null
//            }
//
//            override fun setValue(value: PersianCalendar) {
//                // todo
//                manualCall = false
////                calendarWeeksViewFragment?.setToolbarDate(value)
//            }
//        })
//        calendarHelper!!.visualizeOnRecyclerView(customView?.findViewById(R.id.calendar_list) as RecyclerView?)
//
//
//
//
//   }

    private fun getTodayString(persianCalendar: PersianCalendar): String {
        return Utils.localeNumber(persianCalendar.persianWeekDayName + " " + persianCalendar.persianDay + " " + persianCalendar.persianMonthName + " " + persianCalendar.persianYear)
    }

    fun getResString(res: Int): String {
        return view!!.context!!.getString(res)
    }

    override fun reInitNeeded(): Boolean {
        return dateContainer==null || !(currentDay.persianYear == dateContainer!!.value.persianYear
                && currentDay.persianMonth == dateContainer!!.value.persianMonth
                && currentDay.persianDay == dateContainer!!.value.persianDay)
    }


    override fun onPageShow() {
//        com.activeandroid.util.Log.d(calendarHelper?.date!!)
//        calendarWeeksViewFragment?.setToolbarDate(calendarHelper?.date!!)
    }


    companion object {
        fun getInflatedView(inflater: LayoutInflater?, container: ViewGroup?): View {
            return inflater!!.inflate(R.layout.calendar_day_view, container, false)
        }

        fun getLayoutRes(): Int {
            return R.layout.calendar_day_view
        }
    }
}
