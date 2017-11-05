package com.paraxco.calendarview.Adapters.RecyclerViewPagerAdapters


import android.support.v4.view.ViewPager
import com.paraxco.calendarview.Adapters.EndlessFragmentAdapter

/**
 *
 */

class EndlessRecyclerAdapter(viewPager: ViewPager) : FragmentToRecyclerViewPagerAdapter(viewPager.context, EndlessFragmentAdapter(null, viewPager)) {
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

    fun getEndlessFragmentAdapter(): EndlessFragmentAdapter {
        return getFragmentAdapter() as EndlessFragmentAdapter
    }
}
