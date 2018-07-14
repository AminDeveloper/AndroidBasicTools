package ir.mo.amin.hosseini.calendarview.Adapters.RecyclerViewPagerAdapters

import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import ir.mo.amin.hosseini.calendarview.Adapters.InstantaneousEndlessAdapter.InstantaneousEndlessAdapter
import ir.mo.amin.hosseini.calendarview.Fragments.CalendarFragments.Containers.FragmentContainer

/**
 *
 */

class InstaneousEndlessRecyclerAdapter(container: FragmentContainer, viewPager: ViewPager, manager: FragmentManager?) :
        FragmentToRecyclerViewPagerAdapter(viewPager.context, InstantaneousEndlessAdapter(container, viewPager, null), manager) {
    init {
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(position: Int) {
                getViewFragment(position).onShowingView()
            }
        })
    }

    fun getInstantaneousEndlessAdapter(): InstantaneousEndlessAdapter {
        return getFragmentAdapter() as InstantaneousEndlessAdapter
    }
}
