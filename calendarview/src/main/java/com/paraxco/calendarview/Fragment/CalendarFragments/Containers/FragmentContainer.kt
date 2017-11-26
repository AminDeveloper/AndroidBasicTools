package com.paraxco.calendarview.Fragments.CalendarFragments.Containers;

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.paraxco.calendarview.Adapters.RecyclerViewPagerAdapters.FragmentHolder
import com.paraxco.calendarview.Adapters.RecyclerViewPagerAdapters.InstaneousEndlessRecyclerAdapter
import com.paraxco.calendarview.Fragments.CalendarFragment
import com.paraxco.calendarview.Fragments.CalendarFragments.CalendarPages.CalendarDateFragment
import com.paraxco.calendarview.Fragments.CalendarFragments.ListSlidingMenue
import com.paraxco.calendarview.Helpers.CalendarHelpers.CalendarViewManager
import com.paraxco.calendarview.Interface.ValueContainer
import com.paraxco.calendarview.R
import com.paraxco.commontools.Fragment.BaseFragment
import com.paraxco.commontools.Utils.SmartLogger
import com.paraxco.commontools.Utils.Utils
import com.paraxco.listtools.ListTools.ViewGroupSwitcher.ViewGroupSwitcher
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import java.util.concurrent.atomic.AtomicInteger
import java.util.logging.Logger


/**
 * management for:
 * endless for date fragments for all view types
 * Toolbar date picker
 *
 */
abstract class FragmentContainer : BaseFragment(), AdapterView.OnItemSelectedListener, FragmentHolder.ViewFragment, ViewGroupSwitcher.ViewContainer {

    internal var vpAdapter: InstaneousEndlessRecyclerAdapter? = null
    var dateContainer: ValueContainer<PersianCalendar>? = null// day changes will bo through it
    var calendarViewManager: CalendarViewManager? = null

    companion object {
        val LOG = Logger.getLogger(FragmentContainer::class.java.name)
    }

    init {
    }

    private var contentView: View? = null
    override fun getViewRes(): Int {
        return R.layout.calendar_weeks_parent
    }

    override fun getContext(): Context {
        return getView()!!.context
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView(view)
    }

    override fun setView(view: View?) {
        SmartLogger.logDebug("day number" + dateContainer!!.value.persianDay)

        computerSelect.set(0)//reset computerSelect when view selected
//        ChangeDateObserverHandler.instance.addObserver(this)
        contentView = view
//        clearFindViewByIdCache()
        initializeToolbarDateSelectionMenu()
        viewPagerInit(dateContainer!!.value)

//        refreshCalander()
        getViewByID(R.id.page_title).setOnClickListener(getViewSelectionListener())
        getViewByID(R.id.tree_point).setOnClickListener(getViewSelectionListener())
    }

    override fun onShowingView() {

        if (calendarViewManager!!.getCurrentDateFragment().reInitNeeded())
            view = contentView;
    }

    override fun onHidingView() {

    }

    override fun detachView() {
//        ChangeDateObserverHandler.instance.removeObserver(this)
    }

//    override fun observe() {
////        updateFragmentDate(dateContainer!!.value)
//    }

    override fun getView(): View? {
        return contentView
    }

    fun setCalendarViewManager(calendarViewManager: CalendarViewManager): FragmentContainer {
        this.calendarViewManager = calendarViewManager
        dateContainer = calendarViewManager
        return this
    }
////    fun setDateContainer(calendarActivity: ValueContainer<PersianCalendar>): FragmentContainer {
////        this.dateContainer = calendarActivity
////        return this
//    }

    private var computerSelect: AtomicInteger = AtomicInteger()

//    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater!!.inflate(R.layout.calendar_weeks_parent, container, false)
//        return view
//    }

    open fun viewPagerInit(persianCalendar: PersianCalendar) {
        vpAdapter = InstaneousEndlessRecyclerAdapter(this, getCalendarViewPager(), CalendarFragment.calendarFragment!!.fragmentManager)
        getCalendarViewPager().adapter = vpAdapter

        initialzePagerToDate(persianCalendar)

        updateToolbarWithItem(getCalendarViewPager().currentItem)
        getCalendarViewPager().addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                updateToolbarWithItem(position)
            }
        })
    }

    private fun getCalendarViewPager(): ViewPager {
        return getViewByID(R.id.calendar_pager) as ViewPager
    }

    /**
     * initialize first pages for zero point and left and right side
     */
    abstract fun initialzePagerToDate(persianCalendar: PersianCalendar)

    abstract fun getFragmentAtPoint(relativePosition: Int): CalendarDateFragment

    internal fun updateToolbarWithItem(position: Int) {
        var selectedDateFragment = vpAdapter?.getInstantaneousEndlessAdapter()?.getItem(position) as CalendarDateFragment
        setToolbarDate(selectedDateFragment.getData()!!)
    }

    /**
     * Reinitialize view pager with new date
     */
    fun updateFragmentDate(persianCalendar: PersianCalendar) {
//        if (!vpAdapter!!.gotoDate(persianCalendar)) {
        initialzePagerToDate(persianCalendar)
        vpAdapter?.notifyDataSetChanged()
//        }

//        calendar_week_pager.adapter = vpAdapter


//        var currentWeek=
//        for (i in 0..vpAdapter!!.count - 1) {
//           (vpAdapter?.getItem(i) as CalendarWeekFragment)
//        }
//        for (i in 0..vpAdapter!!.count - 1) {
//            (vpAdapter?.getItem(i) as CalendarWeekFragment).setDate(persianCalendar)
//        }
//        vpAdapter?.onNotifyDataSetChanged()

    }

//    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initializeToolbarDateSelectionMenu()
//        System.out.println("day number" + dateContainer!!.value.persianDay)
//        viewPagerInit(dateContainer!!.value)
////        refreshCalander()
//        page_title.setOnClickListener(getViewSelectionListener())
//        tree_point.setOnClickListener(getViewSelectionListener())
//    }

    /**
     * @return Selection listener for view type selection menu
     */
    private fun getViewSelectionListener(): View.OnClickListener {

        return object : View.OnClickListener {
            override fun onClick(v: View?) {
                val calendarDialogMenu = ListSlidingMenue()
                calendarDialogMenu.setTitleList(listOf("ماه", "هفته", "روز"))
                calendarDialogMenu.selectListener = object : ListSlidingMenue.ItemSelectedListener {
                    override fun onItemSelected(i: Int) {

//                        CalendarListHelper.recyclerViewEvent.visibility = View.GONE
//                        if (i == 1){
//                            CalendarListHelper.recyclerViewEvent.visibility = View.VISIBLE}
                        SmartLogger.logDebug("selected id :" + i)
                        this@FragmentContainer.calendarViewManager!!.selectPage(i)
//                    Toast.makeText(context, i.toString(), Toast.LENGTH_LONG).show()
//                    when (i) {
//                        0 -> Toast.makeText(context, 1.toString(), Toast.LENGTH_LONG).show()
//                        1 -> Toast.makeText(context, 2.toString(), Toast.LENGTH_LONG).show()


//                    }
                    }
                }
                calendarDialogMenu.show(calendarViewManager!!.supportFragmentManager)
            }
        }
    }


    private fun initializeToolbarDateSelectionMenu() {
//        setToolbarDate(PersianCalendar())
        (getViewByID(R.id.month_spiner) as Spinner).onItemSelectedListener = this
        (getViewByID(R.id.year_spiner) as Spinner).onItemSelectedListener = this
        val yearList = mutableListOf<String>()

        for (i in 85..98) {
            yearList.add(Utils.localeNumber(i.toString()))
        }
        //initializing drop down adapter to customize menu item's view
        val adapter = object : ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, yearList) {

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val textView = super.getView(position, convertView, parent) as TextView
                textView.setTextColor(ContextCompat.getColor(context, R.color.white))
                return textView
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val textView = super.getView(position, convertView, parent) as TextView
                textView.setTextColor(ContextCompat.getColor(context, R.color.white))
                return textView
            }
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        (getViewByID(R.id.year_spiner) as Spinner).adapter = adapter
    }

    internal fun getViewByID(res: Int): View {
        return getView()!!.findViewById(res)
    }

    private fun getTodayString(persianCalendar: PersianCalendar): String {
        return Utils.localeNumber(getContext().getString(R.string.today) + " " + persianCalendar.persianWeekDayName + " " + persianCalendar.persianDay + " " + persianCalendar.persianMonthName + " " + persianCalendar.persianYear)
    }

    /**
     * Listener for year and month spiner in tollbar for date selection
     */
    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        if (checkComputerSelect())
            refreshCalander()
        else
            SmartLogger.logDebug("Skiping toolbar select" + "computerSelect =" + computerSelect.get())
    }

    /**
     * checks if last data change is programmatically by computer or by user
     */
    private fun checkComputerSelect(): Boolean {
        SmartLogger.logDebug("computerSelect =" + computerSelect.get())

//        synchronized(this) {
        return computerSelect.getAndDecrement() <= 0
//        }

    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }

    private fun refreshCalander() {
        val year = Integer.parseInt("13" + (getViewByID(R.id.year_spiner) as Spinner)!!.selectedItem)
        val month = (getViewByID(R.id.month_spiner) as Spinner).selectedItemPosition + 1
        val day = 1

        val persianCalendar: PersianCalendar = PersianCalendar()
        persianCalendar.setPersianDate(year, month, day)
        dateContainer?.value = persianCalendar
//        calendarHelper!!.selectDate(year, month)

        updateFragmentDate(persianCalendar)//todo is not true now
    }

    override fun onPageShow() {
//        if (view != null && isAttached)
//        updateFragmentDate(dateContainer!!.value)//todo not working now ViewFragment not fragment!!!!!
//        SmartLogger.logDebug()
    }


    fun setToolbarDate(value: PersianCalendar) {

//        LOG.warning("mouth spinner: ")
//        LOG.warning(month_spiner.selectedItemPosition.toString())
//        LOG.warning((value.persianMonth - 1).toString())

        //if spinner does not contain valid content sets this data change event as Programmatically selected event to be ignored in onItemSelected
        val monthSpinner = getViewByID(R.id.month_spiner) as Spinner
        val monthPosition = value.persianMonth - 1
        if (!(monthSpinner).selectedItemPosition.equals(monthPosition)) {
            computerSelect.incrementAndGet()
            monthSpinner!!.setSelection(monthPosition, false)
        }
        val yearSpinner = getViewByID(R.id.year_spiner) as Spinner
        val yearPosition = value.persianYear - 1385
        if (!yearSpinner.selectedItemPosition.equals(yearPosition)) {
            computerSelect.incrementAndGet()
            yearSpinner!!.setSelection(yearPosition, false)
        }
        SmartLogger.logDebug("computerSelect" + computerSelect.get())
    }


}




