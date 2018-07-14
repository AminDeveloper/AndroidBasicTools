package ir.mo.amin.hosseini.calendarview.Fragments.CalendarFragments.Containers

import android.support.v4.view.ViewPager
import ir.mo.amin.hosseini.calendarview.Fragments.CalendarFragments.CalendarPages.CalendarDateFragment
import ir.mo.amin.hosseini.calendarview.Fragments.CalendarFragments.CalendarPages.CalendarDayFragment
import ir.mo.amin.hosseini.calendarview.R
import ir.mo.amin.hosseini.commontools.Utils.SmartLogger
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import java.util.concurrent.atomic.AtomicInteger
import java.util.logging.Logger

/**
 * container for all days
 */

class CalendarDaysViewFragment : FragmentContainer() {

    companion object {
        val LOG = Logger.getLogger(CalendarWeeksViewFragment::class.java.name)
    }

    private var machinSelect: AtomicInteger = AtomicInteger(0)

    private var persianCalendar: PersianCalendar? = null

    override fun initialzePagerToDate(persianCalendar: PersianCalendar) {
//        machinSelect.set(1)

        this.persianCalendar = PersianCalendar(persianCalendar.timeInMillis)
        SmartLogger.logDebug("fixxed! chnaged")
//        vpAdapter?.getInstantaneousEndlessAdapter()?.clearAll()

        (getViewByID(R.id.calendar_pager) as ViewPager).currentItem = vpAdapter!!.getInstantaneousEndlessAdapter().zeroPoint()
        vpAdapter?.notifyDataSetChanged()
        initDateChangelistener()

    }

    override fun getFragmentAtPoint(relativePosition: Int): CalendarDateFragment {
        SmartLogger.logDebug("fixxed!: "+persianCalendar!!.persianDay.toString())
        var tempCalendar = PersianCalendar(persianCalendar!!.timeInMillis)
        SmartLogger.logDebug((-relativePosition).toString())
        tempCalendar.add(PersianCalendar.DATE, -relativePosition)
        SmartLogger.logDebug("added "+-relativePosition+" days ="+tempCalendar!!.persianDay)

//        tempCalendar.setPersianDate(tempCalendar.persianYear, tempCalendar.persianMonth, tempCalendar.persianDay - relativePosition)
        return CalendarDayFragment().setDate(tempCalendar!!).setDateContainer(dateValueContainer =calendarViewManager!!).setCalendarViewManager(calendarViewManager!!) as CalendarDayFragment
    }


//    override fun initialzePagerToDate(persianCalendar: PersianCalendar) {
//        machinSelect.set(1)
//        initLoaders()
//        vpAdapter?.getEndlessFragmentAdapter()?.DESC = "Days"
//        System.out.println("Days" + "initialzePagerToDate")
//
//        vpAdapter?.getEndlessFragmentAdapter()?.clearAll()
//
//        val currentDay = CalendarDayFragment().setDate(persianCalendar).setCalendarViewManager(calendarViewManager!!) as CalendarDayFragment
//
//
//        var temp = getNextDay(currentDay.getData()?.getCopy())
//
//        for(i in 1..5){
//            vpAdapter?.getEndlessFragmentAdapter()?.addFragmentFromBeginingNoNotify(temp)
//            temp=getNextDay(temp.getData()?.getCopy())
//
//        }
//
//        vpAdapter?.getEndlessFragmentAdapter()?.addFragmentNoNotify(currentDay)
//
//        temp=getPreviousDay(currentDay.getData()?.getCopy())
//        for(i in 1..5){
//            vpAdapter?.getEndlessFragmentAdapter()?.addFragmentNoNotify(temp)
//            temp=getPreviousDay(temp.getData()?.getCopy())
//
//        }
//
//
////        vpAdapter?.getEndlessFragmentAdapter()?.addFragmentFromBeginingNoNotify(getNextDay(currentDay.getData()?.getCopy()))
////        vpAdapter?.getEndlessFragmentAdapter()?.addFragmentNoNotify(currentDay)
////        vpAdapter?.getEndlessFragmentAdapter()?.addFragmentNoNotify(getPreviousDay(currentDay.getData()?.getCopy()))
//
//
//        (getViewByID(R.id.calendar_pager) as ViewPager).currentItem = vpAdapter!!.getEndlessFragmentAdapter().zeroPoint()
//
//        vpAdapter?.notifyDataSetChanged()
////        updateToolbarWithItem((getViewByID(R.id.calendar_pager) as ViewPager).currentItem)
////        calendar_pager.clearOnPageChangeListeners()
//       initDateChangelistener()
//    }

    private fun initDateChangelistener() {
        (getViewByID(R.id.calendar_pager) as ViewPager).addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                //day change means day select so it's needed to change global date
                System.out.println("Day Select " + position)
                val curentDate = getCalendarDateItem(position).getData()
                System.out.println("Day curentDate day1 " + curentDate!!.persianDay)

                if (machinSelect.getAndDecrement() <= 0) {//skip initial values and just set user selections

                    dateContainer!!.value = curentDate
//                    val CopyCalendarDayFragment: CalendarDayFragment = getCalendarDateItem(position) as CalendarDayFragment
//                    CopyCalendarDayFragment.iniitializeReminders()//todo uncomment and see the bug
                }
            }
        })
    }

    private fun getCalendarDateItem(position: Int): CalendarDateFragment {
        return vpAdapter!!.getInstantaneousEndlessAdapter().getItem(position) as CalendarDayFragment
    }

//    override fun observe() {
////        super.observeReminderChange()//toprevent lopp in date select and observer
//    }

//    private fun initLoaders() {
//
//        vpAdapter?.getEndlessFragmentAdapter()?.endlessLoader = object : EndlessFragmentAdapter.EndlessLoader {
//            override fun loadBefore(position: Int): List<Fragment> {
//                var firstWeek = vpAdapter?.getEndlessFragmentAdapter()?.getFirstOfAll() as CalendarDayFragment
//                val listofFragments = LinkedList<Fragment>()
//
//                for (i: Int in 1..1) {
//                    val firstDate: PersianCalendar? = PersianCalendar(firstWeek.getData()!!.timeInMillis)
//                    firstWeek = getPreviousDay(firstDate)
//                    listofFragments.add(firstWeek)
//                }
//                return listofFragments
//            }
//
//            override fun loadAfter(position: Int): List<Fragment> {
//                var lastWeek = vpAdapter?.getEndlessFragmentAdapter()?.getLastOfAll() as CalendarDayFragment
//                val listofFragments = LinkedList<Fragment>()
//
//                for (i: Int in 1..1) {
//                    val lastDate: PersianCalendar? = PersianCalendar(lastWeek.getData()!!.timeInMillis)
//                    lastWeek = getNextDay(lastDate)
//                    listofFragments.add(lastWeek)
//                }
//
//                return listofFragments
//            }
//
//        }
//    }
//
//
//    /**
//     * calculates a week after last item and returns its fragment
//     */
//    private fun getNextDay(lastDate: PersianCalendar?): CalendarDayFragment {
//        val calendarDayFragment: CalendarDayFragment
////        if (lastDate?.persianMonth != 12)
//        lastDate?.addPersianDate(Calendar.DATE, 1)
//        calendarDayFragment = CalendarDayFragment().setDate(lastDate!!).setCalendarViewManager(calendarViewManager!!) as CalendarDayFragment
//        return calendarDayFragment
//    }
//
//    /**
//     * calculates a week before first item and returns its fragment
//     */
//    private fun getPreviousDay(lastDate: PersianCalendar?): CalendarDayFragment {
//        val calendarMonthFragment: CalendarDayFragment
////        if (lastDate?.persianMonth != 12)
//        lastDate?.addPersianDate(Calendar.DATE, -1)
//        calendarMonthFragment = CalendarDayFragment().setDate(lastDate!!).setCalendarViewManager(calendarViewManager!!) as CalendarDayFragment
//        return calendarMonthFragment
//    }

    private fun log(s: String) {
        System.out.println(s)
    }
}

private fun PersianCalendar.getCopy(): PersianCalendar {
    return PersianCalendar(timeInMillis)
}
