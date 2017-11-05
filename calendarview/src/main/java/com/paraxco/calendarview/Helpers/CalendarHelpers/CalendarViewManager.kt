package com.paraxco.calendarview.Helpers.CalendarHelpers

import android.support.design.widget.FloatingActionButton
import android.support.v4.app.FragmentManager
import android.view.View
import android.view.ViewGroup
import com.paraxco.calendarview.Adapters.RecyclerViewPagerAdapters.FragmentViewSwitcher
import com.paraxco.calendarview.Fragments.CalendarFragments.CalendarPages.CalendarDateFragment
import com.paraxco.calendarview.Fragments.CalendarFragments.Containers.CalendarDaysViewFragment
import com.paraxco.calendarview.Fragments.CalendarFragments.Containers.CalendarMonthsFragment
import com.paraxco.calendarview.Fragments.CalendarFragments.Containers.CalendarWeeksViewFragment
import com.paraxco.calendarview.Fragments.CalendarFragments.Containers.FragmentContainer
import com.paraxco.calendarview.Helpers.Observers.ChangeDateObserverHandler
import com.paraxco.calendarview.Interface.ValueContainer
import com.paraxco.calendarview.Model.CalendarModels.ReminderData
import com.paraxco.commontools.Utils.SmartLogger
import ir.hamsaa.persiandatepicker.util.PersianCalendar

/**
 * All managements on fragment containers
 * contains Date value for all pages and initiates containers as view types in view pager
 * give access to all view types and fragment
 *
 */
class CalendarViewManager(val supportFragmentManager: FragmentManager,var viewGroup:ViewGroup , val fab: FloatingActionButton, val contentView: View) : ValueContainer<PersianCalendar> {
    /**
     * holds current selected date for all pages
     */
    var curentDate = PersianCalendar()

    override fun getValue(): PersianCalendar {
        return curentDate;
    }

    override fun setValue(value: PersianCalendar) {
        curentDate.timeInMillis = value.timeInMillis//copying date millisecont and not references to avoid having two reference in different places
        System.out.println("setting day number" + curentDate!!.persianDay)
        ChangeDateObserverHandler.instance.informObservers()
//        vpAdapter?.notifyDataSetChanged()
    }

    /**
     * adapter for view types
     */
//    private var vpAdapter: CalendarViewTypeRecyclerViewPagerAdapter? = null

    private var fragmentViewSwitcher: FragmentViewSwitcher? = null

    var initialPageNumber: Int = 0

    fun viewPagerInit() {
        SmartLogger.logDebug("init")

//        vpAdapter = CalendarViewTypeRecyclerViewPagerAdapter(calendarViewPager)
        //initiating three view types
//        vpAdapter?.getViewPagerAdapter()?.addFragment(CalendarMonthsFragment().setCalendarViewManager(this), "")//todo uncomment it
//        vpAdapter?.getViewPagerAdapter()?.addFragment(CalendarWeeksViewFragment().setCalendarViewManager(this), "")
//        vpAdapter?.getViewPagerAdapter()?.addFragment(CalendarDaysViewFragment().setCalendarViewManager(this), "")//todo uncomment it
//        calendarViewPager.adapter = vpAdapter
//        calendarViewPager.currentItem = initialPageNumber
//        calendarViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrollStateChanged(state: Int) {
//
//            }
//
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//
//            }
//
//            override fun onPageSelected(position: Int) {
//                SmartLogger.logDebug("listener select: " + position.toString())
//                vpAdapter?.notifyDataSetChanged()//when view changes new view should reinitialize themself
//
//            }
//        })

        fragmentViewSwitcher = FragmentViewSwitcher(viewGroup)
        //initiating three view types
        fragmentViewSwitcher?.addView(CalendarMonthsFragment().setCalendarViewManager(this))
        fragmentViewSwitcher?.addView(CalendarWeeksViewFragment().setCalendarViewManager(this))
        fragmentViewSwitcher?.addView(CalendarDaysViewFragment().setCalendarViewManager(this))
//                fragmentViewSwitcher?.addView(object :FragmentHolder.ViewFragment{
//                    var thisView: View? = null
//                    override fun setView(view: View?) {
//                        thisView=view
//                    }
//
//                    override fun detachView() {
//                    }
//
//                    override fun getView(): View? {
//                        return thisView
//                    }
//
//                    override fun onShowingView() {
//                    }
//
//                    override fun getViewRes(): Int {
//                        return R.layout.calendar_mounth_view
//                    }
//
//                })

        fragmentViewSwitcher?.showView(initialPageNumber)
//        fragmentViewSwitcher?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrollStateChanged(state: Int) {
//
//            }
//
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//
//            }
//
//            override fun onPageSelected(position: Int) {
//                SmartLogger.logDebug("listener select: " + position.toString())
////                vpAdapter?.notifyDataSetChanged()//when view changes new view should reinitialize themself
//
//            }
//        })



        fab!!.setOnClickListener {
            //opens new reminder dialog
            CalendarDialogReminder(this.contentView.context, getCurrentDateFragment(), getCurrentDateFragment().getData()).showDialog()
        }

    }

    /**
     * changes view type
     */
    fun selectPage(pageNum: Int) {
//        SmartLogger.logDebug("Selecting ==> curent=" + fragmentViewSwitcher?.currentItem + "size" + vpAdapter!!.count)
//        calendarViewPager.post(Runnable {
//            SmartLogger.logDebug("in runable")
        fragmentViewSwitcher?.showView(pageNum)
//        })
        SmartLogger.logDebug("Selected $pageNum")
    }

    /**
     * @return curent view type or -1 when not initialized
     */
    fun getCurrentPage(): Int {
        if (fragmentViewSwitcher == null ||fragmentViewSwitcher?.currentItem==null)
            return -1
        return fragmentViewSwitcher!!.currentItem!!
    }

    fun refreshReminders(reminderData: ReminderData) {
        getCurrentDateFragment().refreshView()
    }

    /**
     * @return current CalendarDateFragment wich is showing
     */
    fun getCurrentDateFragment(): CalendarDateFragment {
//        var fragments = vpAdapter?.getAllFragments()
        var viewInShow = fragmentViewSwitcher?.getItem(fragmentViewSwitcher!!.currentItem!!) as FragmentContainer

        var fragmentInShow = viewInShow.vpAdapter?.getItem(viewInShow.vpAdapter!!.getEndlessFragmentAdapter().viewPager.currentItem) as CalendarDateFragment
        return fragmentInShow
    }

    /**
     * @return current FragmentContainer wich is showing
     */
    fun getCurrentContainerFragment(): FragmentContainer? {
//        var fragments = vpAdapter?.getAllFragments()
//        var viewInShow = vpAdapter?.getItem(calendarViewPager.currentItem) as FragmentContainer

        return fragmentViewSwitcher?.getItem(fragmentViewSwitcher!!.currentItem!!) as FragmentContainer
    }


}