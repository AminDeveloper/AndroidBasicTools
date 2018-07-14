package ir.mo.amin.hosseini.calendarview.Fragments.CalendarFragments.Containers

import android.support.v4.view.ViewPager
import ir.mo.amin.hosseini.calendarview.Adapters.RecyclerViewPagerAdapters.FragmentHolder
import ir.mo.amin.hosseini.listtools.ListTools.ViewGroupSwitcher.ViewGroupSwitcher
import ir.mo.amin.hosseini.calendarview.Fragments.CalendarFragments.CalendarPages.CalendarDateFragment
import ir.mo.amin.hosseini.calendarview.Fragments.CalendarFragments.CalendarPages.CalendarMonthFragment
import ir.mo.amin.hosseini.calendarview.R
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import java.util.logging.Logger

/**
 * container for All months
 */
class CalendarMonthsFragment : FragmentContainer(), FragmentHolder.ViewFragment, ViewGroupSwitcher.ViewContainer {

    companion object {
        val LOG = Logger.getLogger(CalendarWeeksViewFragment::class.java.name)
    }

    private  var persianCalendar: PersianCalendar?=null

    override fun initialzePagerToDate(persianCalendar: PersianCalendar) {
        this.persianCalendar = PersianCalendar(persianCalendar.timeInMillis)
        (getViewByID(R.id.calendar_pager) as ViewPager).currentItem = vpAdapter!!.getInstantaneousEndlessAdapter().zeroPoint()
                vpAdapter?.notifyDataSetChanged()
    }

    override fun getFragmentAtPoint(relativePosition: Int): CalendarDateFragment {
        var tempCalendar=PersianCalendar(persianCalendar!!.timeInMillis)
//        tempCalendar.add(PersianCalendar.MONTH,-relativePosition)
        tempCalendar.setPersianDate(tempCalendar.persianYear, tempCalendar.persianMonth-relativePosition, tempCalendar.persianDay)

        return getCalendarMonthFragment(tempCalendar)
    }

//    override fun initialzePagerToDate(persianCalendar: PersianCalendar) {
//        val startTime=System.currentTimeMillis()
//
//        initLoaders()
//        vpAdapter?.getEndlessFragmentAdapter()?.DESC = "Months"
//        System.out.println("Months" + "initialzePagerToDate")
//
////        vpAdapter?.layoutRes = CalendarMonthFragment.getLayoutRes()
////        vpAdapter?.endlessFragmentAdapter?.noEndless = true
//        vpAdapter?.getEndlessFragmentAdapter()?.clearAll()
//
//        val currentMonth = getCalendarMonthFragment(persianCalendar)
//        var temp = getNextMonth(currentMonth.getData()?.getCopy())
//
//        for(i in 1..5){
//            vpAdapter?.getEndlessFragmentAdapter()?.addFragmentFromBeginingNoNotify(temp)
//            temp=getNextMonth(temp.getData()?.getCopy())
//        }
//
//        vpAdapter?.getEndlessFragmentAdapter()?.addFragmentNoNotify(currentMonth)
//
//        temp=getPreviousMonth(currentMonth.getData()?.getCopy())
//        for(i in 1..5){
//            vpAdapter?.getEndlessFragmentAdapter()?.addFragmentNoNotify(temp)
//            temp=getPreviousMonth(temp.getData()?.getCopy())
//        }
//
////        vpAdapter?.getEndlessFragmentAdapter()?.addFragmentNoNotify(getPreviousMonth(currentMonth.getData()?.getCopy()))
//        //Log.e("dfdfdfdfd","dsdwdwdw")
//
//        (getViewByID(R.id.calendar_pager) as ViewPager).currentItem = vpAdapter!!.getEndlessFragmentAdapter().zeroPoint()
//
//        vpAdapter?.notifyDataSetChanged()
////        updateToolbarWithItem((getViewByID(R.id.calendar_pager) as ViewPager).currentItem)
//        val endtTime=System.currentTimeMillis()
//        SmartLogger.logDebug("took "+(endtTime-startTime)+" millisecond")
//    }

    private fun getCalendarMonthFragment(persianCalendar: PersianCalendar): CalendarMonthFragment {
        return CalendarMonthFragment().setDate(persianCalendar).setDateContainer(dateContainer!!).setCalendarViewManager(calendarViewManager!!) as CalendarMonthFragment
    }

//    private fun initLoaders() {
//
//        vpAdapter?.getEndlessFragmentAdapter()?.endlessLoader = object : EndlessFragmentAdapter.EndlessLoader {
//            override fun loadBefore(position: Int): List<Fragment> {
//                var firstMonth = vpAdapter?.getEndlessFragmentAdapter()?.getFirstOfAll() as CalendarMonthFragment
//                val listofFragments = LinkedList<Fragment>()
//
//                for (i: Int in 1..5) {
//                    val firstDate: PersianCalendar? = PersianCalendar(firstMonth.getData()!!.timeInMillis)
//                    firstMonth = getPreviousMonth(firstDate)
//                    listofFragments.add(firstMonth)
//                }
//                return listofFragments
//            }
//
//            override fun loadAfter(position: Int): List<Fragment> {
//                var lastWeek = vpAdapter?.getEndlessFragmentAdapter()?.getLastOfAll() as CalendarMonthFragment
//                val listofFragments = LinkedList<Fragment>()
//
//                for (i: Int in 1..5) {
//                    val lastDate: PersianCalendar? = PersianCalendar(lastWeek.getData()!!.timeInMillis)
//                    lastWeek = getNextMonth(lastDate)
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
//    private fun getNextMonth(lastDate: PersianCalendar?): CalendarMonthFragment {
//        val calendarMonthFragment: CalendarMonthFragment
//        if (lastDate?.persianMonth != 12)
//            lastDate?.setPersianDate(lastDate.persianYear, lastDate.persianMonth + 1, 1)
//        else
//            lastDate.setPersianDate(lastDate.persianYear + 1, 1, 1)
//        calendarMonthFragment = getCalendarMonthFragment(lastDate!!)
//        return calendarMonthFragment
//    }
//
//    /**
//     * calculates a week before first item and returns its fragment
//     */
//    private fun getPreviousMonth(firstDate: PersianCalendar?): CalendarMonthFragment {
//        var calendarMonthFragment: CalendarMonthFragment
//        if (firstDate?.persianMonth == 1)
//            firstDate.setPersianDate(firstDate.persianYear - 1, 12, 1)
//        else
//            firstDate?.setPersianDate(firstDate.persianYear, firstDate.persianMonth - 1, 1)
//        calendarMonthFragment = getCalendarMonthFragment(firstDate!!)
//        return calendarMonthFragment
//    }

    private fun log(s: String) {
        System.out.println(s)
    }
}

private fun PersianCalendar.getCopy(): PersianCalendar {
    return PersianCalendar(timeInMillis)
}
