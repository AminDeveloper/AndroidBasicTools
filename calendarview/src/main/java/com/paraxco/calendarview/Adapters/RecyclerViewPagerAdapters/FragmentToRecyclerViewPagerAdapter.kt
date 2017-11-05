package com.paraxco.calendarview.Adapters.RecyclerViewPagerAdapters

import android.content.Context
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * All Fragments in fragmentAdapter must implement ViewFragment and manage initializing in setView with presented View not Fragments View
 * for example FindViewById should be Used with this View
 * Note recycled Views!!
 * all Context fun such as getContext or getActivity will not work
 * you can getContext from View
 *
 */

open class FragmentToRecyclerViewPagerAdapter(var context: Context, private var fragmentAdapter: NotifyAwareAdapter) : RecyclerPagerAdapter<FragmentHolder>() {

    init {
        fragmentAdapter.setOnNotifyDataSetChange(object : NotifyDataSetChangedListener {
            override fun onNotifyDataSetChanged() {
                notifyDataSetChanged()
            }
        })
    }

    /**
     * @return real adapter
     */
    fun getFragmentAdapter(): NotifyAwareAdapter {
        return fragmentAdapter
    }

    override fun getItemCount(): Int {
        return fragmentAdapter.getCount()
    }

    override fun onBindViewHolder(holder: FragmentHolder?, position: Int) {
        holder?.bind(getViewFragment(position))
    }


     fun getViewFragment(position: Int): FragmentHolder.ViewFragment {
        return fragmentAdapter.getItem(position) as FragmentHolder.ViewFragment
    }

    override fun getItemViewType(position: Int): Int {
        return position//all item have different type
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FragmentHolder {
        return FragmentHolder((LayoutInflater.from(context).inflate(getViewFragment(viewType).getViewRes(), parent, false)))
    }

    fun getItem(position: Int): Fragment {
        return fragmentAdapter.getItem(position)
    }

    /**
     * adapter that notifies you when NotifyDataSetChange gets called on it
     */
    interface NotifyAwareAdapter {
        fun setOnNotifyDataSetChange(notifyDataSetChangedListener: NotifyDataSetChangedListener)
        fun getItem(position: Int): Fragment
        fun getCount(): Int

    }

    interface NotifyDataSetChangedListener {
        fun onNotifyDataSetChanged()

    }
}


class FragmentHolder(view: View) : RecyclerPagerAdapter.ViewHolder(view) {
    //    var weekFragment: CalendarWeekFragment = weekFragment
    var viewFragment: ViewFragment? = null;

    fun bind(viewFragment: ViewFragment) {
        this.viewFragment = viewFragment
        viewFragment.setView(itemView)
    }

    override fun onDetach() {
        super.onDetach()

        viewFragment?.detachView()
    }

    /**
     * fragments can act as view in view adapters by implementing this interface
     */
    interface ViewFragment {
        /**
         * will be called by adapter with recycled view
         */
        fun setView(view: View?)

        /**
         * will be called when recycling view
         */
        fun detachView()

        fun getView(): View?
        /**
         * will be called when recycling view is showing this view
         */
        fun onShowingView()
        /**
         * @return requested view res
         */
        fun getViewRes(): Int
    }

}
