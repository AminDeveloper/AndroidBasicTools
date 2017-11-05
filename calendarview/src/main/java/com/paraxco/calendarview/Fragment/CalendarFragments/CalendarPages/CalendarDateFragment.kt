package com.paraxco.calendarview.Fragments.CalendarFragments.CalendarPages

import android.view.View
import com.paraxco.calendarview.Adapters.RecyclerViewPagerAdapters.FragmentHolder
import com.paraxco.calendarview.Helpers.CalendarHelpers.CalendarViewManager
import com.paraxco.calendarview.Interface.ValueContainer
import com.paraxco.commontools.Fragment.BaseFragment
import ir.hamsaa.persiandatepicker.util.PersianCalendar

/**
 * Parent for each day week and month
 *
 */
abstract class CalendarDateFragment : BaseFragment(), FragmentHolder.ViewFragment {
    protected var customView: View? = null
    var selectedNotShowing=false

    var calendarViewManager: CalendarViewManager? = null
    var dateContainer: ValueContainer<PersianCalendar>? = null

    fun setDateContainer(dateValueContainer: ValueContainer<PersianCalendar>): CalendarDateFragment {
        this.dateContainer = dateValueContainer
        return this
    }

    abstract fun getData(): PersianCalendar?

    override fun setView(itemView: View?) {
        this.customView = itemView
//        if(selectedNotShowing)
//            onShowingView()
//        selectedNotShowing=false
    }

    override fun onShowingView() {
        selectedNotShowing=true
    }

    override fun getView(): View? {
        return customView
    }

    fun setCalendarViewManager(calendarViewManager: CalendarViewManager): CalendarDateFragment {
        this.calendarViewManager = calendarViewManager
        return this
    }

    fun refreshView() {
        view = customView;
    }

    fun getViewByID(res: Int): View {
        return view!!.findViewById(res)
    }
}