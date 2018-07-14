package ir.mo.amin.hosseini.calendarview.Adapters.InstantaneousEndlessAdapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import ir.mo.amin.hosseini.calendarview.Adapters.RecyclerViewPagerAdapters.FragmentToRecyclerViewPagerAdapter
import ir.mo.amin.hosseini.calendarview.Fragments.CalendarFragments.Containers.FragmentContainer

/**
 * Created by Amin on 11/5/2017.
 */
class InstantaneousEndlessAdapter(var container: FragmentContainer, var viewPager: ViewPager, manager: FragmentManager?) : FragmentPagerAdapter(manager), FragmentToRecyclerViewPagerAdapter.NotifyAwareAdapter {

    var notifyDataSetChangedListener: FragmentToRecyclerViewPagerAdapter.NotifyDataSetChangedListener? = null


    val zeroPoint = getCount() / 2
    fun zeroPoint(): Int {
        return zeroPoint
    }

    override fun getItem(position: Int): Fragment {
        return container.getFragmentAtPoint(position - zeroPoint)
    }

    override fun getCount(): Int {
        return 5000
    }

    override fun setOnNotifyDataSetChange(notifyDataSetChangedListener: FragmentToRecyclerViewPagerAdapter.NotifyDataSetChangedListener) {
        this.notifyDataSetChangedListener = notifyDataSetChangedListener
    }

    override fun notifyDataSetChanged() {
//        zeroPoint=fragmentListNegetive.size
//        viewPager.currentItem=currentPage-zeroPoint
        super.notifyDataSetChanged()
        notifyDataSetChangedListener?.onNotifyDataSetChanged()
    }


}