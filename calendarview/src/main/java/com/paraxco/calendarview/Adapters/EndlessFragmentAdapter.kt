package com.paraxco.calendarview.Adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.paraxco.calendarview.Adapters.RecyclerViewPagerAdapters.FragmentToRecyclerViewPagerAdapter
import com.paraxco.commontools.Utils.SmartLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*


open class EndlessFragmentAdapter(manager: FragmentManager?, viewPager: ViewPager) : FragmentPagerAdapter(manager), FragmentToRecyclerViewPagerAdapter.NotifyAwareAdapter {
    internal val fragmentListPositive = LinkedList<Fragment>()//last
    internal val fragmentListNegetive = LinkedList<Fragment>()//future
    //note that scroll direction in view pager if from left to right
    val manager: FragmentManager? = manager
    public var treshold: Int = 2//distance to load more
    public var allAllowed: Int = 10//number of item allowed for each list(note that is is not guranteed)
    var endlessLoader: EndlessLoader? = null
    var viewPager: ViewPager = viewPager
    var notifyDataSetChangedListener: FragmentToRecyclerViewPagerAdapter.NotifyDataSetChangedListener? = null
    private var scrollRange: Int = 100

    private var zeroPoint: Int = calculateZeroPoint()
    private var currentPage: Int = 0
    //    public var noEndless=false
    lateinit var DESC: String


    init {
        //        viewPager.currentItem = zeroPoint//dose'nt work because it is befor attache to adapter!
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                currentPage = position
//                if(noEndless)
//                    return
                doAsync {
                    if (Math.abs(position) + treshold >= scrollRange) {
                        uiThread {
                            scrollRange = scrollRange + 100
                            zeroPoint = calculateZeroPoint()
                            notifyDataSetChanged()
                            viewPager.currentItem = position + 6 / 2
                        }
                    }
                    if (doEndlessWorks(position - zeroPoint))
                        uiThread {
                            viewPager.postDelayed({
                                notifyDataSetChanged()
                                SmartLogger.logDebug("notifyDataSetChanged()")
                            }, 2000)

                        }
                }
            }
        })
    }

    private fun calculateZeroPoint(): Int {
        return scrollRange / 2
    }

    public fun zeroPoint(): Int {
        return zeroPoint
    }

//    override fun getCount(): Int {
//        return fragmentListNegetive.size + fragmentListPositive.size
//    }


    override fun getCount(): Int {
        return scrollRange
    }


    override fun getItem(position: Int): Fragment {
//        object : AsyncTask<Any, Any, Any>() {
//            override fun doInBackground(vararg params: Any?): Any {
//
//                return doEndlessWorks(position)
//            }
//
//            override fun onPostExecute(result: Any?) {
//                if (result as Boolean)
//                    onNotifyDataSetChanged()
//                super.onPostExecute(result)
//            }
//
//        }.execute()

//        if(doEndlessWorks(position))
//            onNotifyDataSetChanged()

        var index: Int = position - zeroPoint
        System.out.println(DESC + "index " + index)
        System.out.println(DESC + " pos" + fragmentListPositive.size)
        System.out.println(DESC + " neg" + fragmentListNegetive.size)

//        if(index==-zeroPoint)
//            index=0
        var fragment: Fragment? = null
        try {
            if (index >= 0)
                fragment = fragmentListPositive[index]
            else
                fragment = fragmentListNegetive[-index - 1]//to touch 0 one unit added
        } catch (e: IndexOutOfBoundsException) {

        }
        if (fragment == null) {
            fragment = fragmentListPositive[0]
            viewPager.currentItem = zeroPoint
        }

        return fragment
    }

    override fun notifyDataSetChanged() {
//        zeroPoint=fragmentListNegetive.size
//        viewPager.currentItem=currentPage-zeroPoint
        super.notifyDataSetChanged()
        notifyDataSetChangedListener?.onNotifyDataSetChanged()

    }

    private @Synchronized
    fun doEndlessWorks(position: Int): Boolean {
        if (position >= 0 && fragmentListPositive.size - 1 - position <= treshold) {
            loadBeforePositive(position)
            return true
        }
        if (position < 0 && fragmentListNegetive.size - 1 - (-position - 1) <= treshold) {
            loadAfterNegative(position)
            return true
        }
        return false
    }

    fun loadAfterNegative(position: Int) {
//        doAsync {
        val list = endlessLoader?.loadAfter(position)
//            uiThread {
        addFragmentFromBeginingNoNotify(list)
//                        removeTail(fragmentListPositive)
//            }
//        }
    }

    fun loadBeforePositive(position: Int) {
//        doAsync {
        val list = endlessLoader?.loadBefore(position)
//            uiThread {
        addFragmentNoNotify(list)
        //        removeTail(fragmentListNegetive)
//            }
//        }
    }

//    private fun remove(directionPositive: Boolean) {
//        val extra = fragmentListNegetive.size + fragmentListPositive.size - allAllowed
//        if (extra > 0) {
//            val startPoint: Int
//            if (directionPositive) {
//                startPoint = zeroPoint - (fragmentListNegetive.size - 1)
//                for (i in 0..extra - 1)
//                    fragmentListNegetive.removeAt(startPoint - i)
//            } else {
//                startPoint = zeroPoint + (fragmentListPositive.size - 1)
//                for (i in 0..extra - 1)
//                    fragmentListPositive.removeAt(startPoint - i)
//            }
//        }
//    }

    private fun removeTail(fragmentList: LinkedList<Fragment>) {
        if (fragmentListNegetive.size + fragmentListPositive.size > allAllowed) {

        }

        if (fragmentList.size > allAllowed) {
            for (i in 1..fragmentList.size - allAllowed)
                fragmentList.removeAt(fragmentList.size - i)
        }
    }

    private fun removeHead(fragmentList: LinkedList<Fragment>) {
        if (fragmentList.size > allAllowed) {
            for (i in 1..fragmentList.size - allAllowed)
                fragmentList.removeAt(i - 1)
        }
    }

    fun getItemFromList(position: Int): Fragment {
        return getItem(position)
    }

    fun getFirstOfAll(): Fragment {
        if (fragmentListPositive.size > 0)
            return fragmentListPositive.last
        else
            return fragmentListNegetive.first
    }

    fun getLastOfAll(): Fragment {
        if (fragmentListNegetive.size > 0)
            return fragmentListNegetive.last
        else
            return fragmentListPositive.first
    }

    fun addFragment(fragment: Fragment) {
        fragmentListPositive.add(fragment)
        notifyDataSetChanged()
    }

    fun addFragmentNoNotify(fragment: Fragment) {
        fragmentListPositive.add(fragment)
    }

    fun addFragmentNoNotify(fragments: List<Fragment>?) {
        fragments?.let {
            fragmentListPositive.addAll(it)
        }
    }

    fun addFragment(fragments: List<Fragment>?) {
        fragments?.let {
            fragmentListPositive.addAll(it)
        }
        notifyDataSetChanged()
    }

    fun addFragmentFromBegining(fragments: List<Fragment>?) {
        fragments?.let {
            fragmentListNegetive.addAll(it)
        }
        notifyDataSetChanged()
    }

    fun addFragmentFromBegining(fragment: Fragment) {
        fragment?.let {
            fragmentListNegetive.add(fragment)
        }
        notifyDataSetChanged()
    }

    fun addFragmentFromBeginingNoNotify(fragment: Fragment) {
        fragment?.let {
            fragmentListNegetive.add(fragment)
        }
    }

    fun addFragmentFromBeginingNoNotify(fragments: List<Fragment>?) {
        fragments?.let {
            fragmentListNegetive.addAll(it)
        }
    }

    interface EndlessLoader {
        fun loadAfter(position: Int): List<Fragment>
        fun loadBefore(position: Int): List<Fragment>
    }

    fun clearAll() {
//        val transaction = manager?.beginTransaction()
//
//        for (i in 0..fragmentListPositive.size - 1)
//            transaction?.remove(fragmentListPositive[i])
//        for (i in 0..fragmentListNegetive.size - 1)
//            transaction?.remove(fragmentListNegetive[i])
//        transaction?.commit()
        System.out.println(DESC + " CLEARED!!!")
        fragmentListPositive.clear()
        fragmentListNegetive.clear()

        scrollRange = 100
        currentPage = 0
        zeroPoint = calculateZeroPoint()
//        viewPager.currentItem = 0
//        manager?.executePendingTransactions()
    }

    override fun setOnNotifyDataSetChange(notifyDataSetChangedListener: FragmentToRecyclerViewPagerAdapter.NotifyDataSetChangedListener) {
        this.notifyDataSetChangedListener = notifyDataSetChangedListener

    }


}
