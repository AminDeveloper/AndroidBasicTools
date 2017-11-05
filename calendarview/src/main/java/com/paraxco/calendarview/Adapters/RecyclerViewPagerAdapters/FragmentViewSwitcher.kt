package com.paraxco.calendarview.Adapters.RecyclerViewPagerAdapters

import android.content.Context
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 *
 */
class FragmentViewSwitcher(var viewGroup: ViewGroup) {
    private val viewList = mutableListOf<FragmentHolder.ViewFragment>()
    var currentItem: Int? = null

    private var inflator: LayoutInflater? = null
    var onPageChangeListener: ViewPager.OnPageChangeListener? = null
    fun addView(view: FragmentHolder.ViewFragment) {
        viewList.add(view)
    }

    fun showView(position: Int) {

        viewGroup.removeAllViews()
        viewGroup.addView(getView(position), 0, ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT))
        currentItem = position
        onPageChangeListener?.onPageSelected(position)

    }

    private fun getView(position: Int): View? {
        if (viewList[position].getView() == null) {
            if (inflator == null)
                inflator = viewGroup.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            viewList[position].setView((inflator?.inflate(viewList[position].getViewRes(), viewGroup, false)))
        }else{
            viewList[position].setView(viewList[position].getView())
        }

        return viewList[position].getView()
    }

    fun addOnPageChangeListener(onPageChangeListener: ViewPager.OnPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener
    }

    fun getItem(position: Int): FragmentHolder.ViewFragment {
        return viewList.get(position)
    }


}