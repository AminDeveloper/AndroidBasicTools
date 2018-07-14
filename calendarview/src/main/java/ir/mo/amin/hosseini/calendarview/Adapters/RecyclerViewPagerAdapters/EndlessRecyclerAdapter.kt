package ir.mo.amin.hosseini.calendarview.Adapters.RecyclerViewPagerAdapters


import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import ir.mo.amin.hosseini.calendarview.Adapters.EndlessFragmentAdapter

/**
 *
 */

class EndlessRecyclerAdapter(viewPager: ViewPager, manager: FragmentManager?) : FragmentToRecyclerViewPagerAdapter(viewPager.context, EndlessFragmentAdapter(null, viewPager),manager ) {
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
