package com.paraxco.calendarview.Fragments.CalendarFragments.Containers

import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.paraxco.calendarview.Adapters.EndlessFragmentAdapter
import com.paraxco.calendarview.Adapters.RecyclerViewPagerAdapters.FragmentHolder
import com.paraxco.calendarview.Fragments.CalendarFragments.CalendarPages.CalendarWeekFragment
import com.paraxco.calendarview.Helpers.CalendarHelpers.CalendarListHelpers.CalendarListHelperBase
import com.paraxco.calendarview.R
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import java.util.*
import java.util.logging.Logger

/**
 *container for all weeks
 */

class CalendarWeeksViewFragment : FragmentContainer(),FragmentHolder.ViewFragment {


    companion object {
        val LOG = Logger.getLogger(CalendarWeeksViewFragment::class.java.name)
    }

    override fun initialzePagerToDate(persianCalendar: PersianCalendar) {
        initLoaders()
        vpAdapter?.getEndlessFragmentAdapter()?.DESC = "weeks"
        System.out.println("weeks" + "initialzePagerToDate")

//        vpAdapter?.layoutRes = CalendarWeekFragment.getLayoutRes()

        vpAdapter?.getEndlessFragmentAdapter()?.clearAll()

        val currentWeek = getCalendarWeekFragment(CalendarListHelperBase.weekNumberInMonth(persianCalendar), this, persianCalendar)


        var temp = getNextWeek(currentWeek.getData()?.getCopy(),currentWeek.getCurentWeek())
        for(i in 1..5){
            vpAdapter?.getEndlessFragmentAdapter()?.addFragmentFromBeginingNoNotify(temp)
            temp=getNextWeek(temp.getData()?.getCopy(),temp.getCurentWeek())
        }

        vpAdapter?.getEndlessFragmentAdapter()?.addFragmentNoNotify(currentWeek)

        temp=getPreviousWeek(currentWeek.getData()?.getCopy(),currentWeek.getCurentWeek())
        for(i in 1..5){
            vpAdapter?.getEndlessFragmentAdapter()?.addFragmentNoNotify(temp)
            temp=getPreviousWeek(temp.getData()?.getCopy(),temp.getCurentWeek())

        }


        (getViewByID(R.id.calendar_pager) as ViewPager).currentItem = vpAdapter!!.getEndlessFragmentAdapter().zeroPoint()

        vpAdapter?.notifyDataSetChanged()
//        updateToolbarWithItem((getViewByID(R.id.calendar_pager) as ViewPager).currentItem)
    }

    private fun initLoaders() {

        vpAdapter?.getEndlessFragmentAdapter()?.endlessLoader = object : EndlessFragmentAdapter.EndlessLoader {
            override fun loadBefore(position: Int): List<Fragment> {
                var firstWeek: CalendarWeekFragment = vpAdapter?.getEndlessFragmentAdapter()?.getFirstOfAll() as CalendarWeekFragment
                val listofFragments = LinkedList<Fragment>()

                for (i: Int in 1..5) {
                    val firstDate: PersianCalendar? = PersianCalendar(firstWeek.getData()!!.timeInMillis)
                    firstWeek = getPreviousWeek(firstDate, firstWeek.getCurentWeek())
                    listofFragments.add(firstWeek)
                }
                return listofFragments
            }

            override fun loadAfter(position: Int): List<Fragment> {
                var lastWeek: CalendarWeekFragment = vpAdapter?.getEndlessFragmentAdapter()?.getLastOfAll() as CalendarWeekFragment
                val listofFragments = LinkedList<Fragment>()

                for (i: Int in 1..5) {
                    val lastDate: PersianCalendar? = PersianCalendar(lastWeek.getData()!!.timeInMillis)
                    lastWeek = getNextWeek(lastDate, lastWeek.getCurentWeek())
                    listofFragments.add(lastWeek)
                }

                return listofFragments
            }

        }
    }


    /**
     * calculates a week after last item and returns its fragment
     */
    private fun getNextWeek(lastDate: PersianCalendar?, week: Int): CalendarWeekFragment {
        var calendarWeekFragment: CalendarWeekFragment
        var currentWeek: Int
        if (week < 5) {
            currentWeek = week + 1
            log("i am in increasing just week point!" + week + " month= " + lastDate?.persianMonth)
        } else {
            if (week == 5 && CalendarListHelperBase.dayNumberInWeek(6, lastDate!!) > 0) {
                currentWeek = 6
            } else {
                currentWeek = 1
                log("i am in increasing month point! week= " + week + " month= " + lastDate?.persianMonth)
                lastDate?.setPersianDate(lastDate.persianYear, lastDate.persianMonth + 1, 1)//switch to next week
            }
        }
        log("getNextWeek:i am in creating frament point! currentWeek = " + currentWeek + " month= " + lastDate?.persianMonth)

        calendarWeekFragment = getCalendarWeekFragment(currentWeek, this@CalendarWeeksViewFragment, lastDate!!)
        return calendarWeekFragment
    }


    /**
     * calculates a week before first item and returns its fragment
     */
    private fun getPreviousWeek(firstDate: PersianCalendar?, week: Int): CalendarWeekFragment {
        var calendarWeekFragment: CalendarWeekFragment
        var currentWeek: Int
        if (week > 1) {
            log("i am in decreasing just week point!" + week + " month= " + firstDate?.persianMonth)

            currentWeek = week - 1
        } else {
            log("i am in decreasing month point!" + week + " month= " + firstDate?.persianMonth)
            firstDate?.setPersianDate(firstDate.persianYear, firstDate.persianMonth - 1, 1)
            if (CalendarListHelperBase.dayNumberInWeek(6, firstDate!!) > 0) {
                currentWeek = 6
            } else {
                currentWeek = 5
            }
        }
        log("getPreviousWeek: i am in creating frament point! currentWeek = " + currentWeek + " month= " + firstDate?.persianMonth)
        calendarWeekFragment = getCalendarWeekFragment(currentWeek, this@CalendarWeeksViewFragment, firstDate!!)

        return calendarWeekFragment
    }

    private fun getCalendarWeekFragment(currentWeek: Int, calendarWeeksViewFragment: CalendarWeeksViewFragment, date: PersianCalendar): CalendarWeekFragment {
        return CalendarWeekFragment().requestWeek(currentWeek, this@CalendarWeeksViewFragment).setDate(date).setDateContainer(dateContainer!!).setCalendarViewManager(calendarViewManager!!) as CalendarWeekFragment
    }

    private fun log(s: String) {
        System.out.println(s)
    }
}

private fun PersianCalendar.getCopy(): PersianCalendar {
    return PersianCalendar(timeInMillis)
}
