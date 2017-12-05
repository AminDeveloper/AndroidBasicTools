package com.paraxco.basictools.Activities.ListTools

import android.os.Bundle
import android.widget.Toast
import com.paraxco.basictools.ListTools.Model.CustomItem

import com.paraxco.basictools.R
import com.paraxco.calendarview.Fragments.CalendarFragments.StringListSlidingMenue
import com.paraxco.commontools.Activities.BaseActivity
import com.paraxco.listtools.ListTools.Adapter.RecyclerView.RecyclerViewDataItemAdapter
import com.paraxco.listtools.ListTools.DataItem.DataItemBase
import kotlinx.android.synthetic.main.list_tools_test.*
import com.paraxco.commontools.Utils.Permision.PermisionUtils
import com.paraxco.listtools.ListTools.Dialog.SlidingDialogMenu


/**
 * Created by Amin on 11/6/2017.
 */

class ListToolsTest : BaseActivity() {
    private val items: MutableList<DataItemBase<Any>>?= mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_tools_test)

        button.setOnClickListener {
//            var slidingDialogMenu= SlidingDialogMenu()
//            slidingDialogMenu.items.add(CustomItem(this,1))
//            slidingDialogMenu.items.add(CustomItem(this,2))
//            slidingDialogMenu.items.add(CustomItem(this,3))
//            slidingDialogMenu.items.add(CustomItem(this,4))
//            slidingDialogMenu.items.add(CustomItem(this,5))
//            slidingDialogMenu.items.add(CustomItem(this,6))
//            slidingDialogMenu.items.add(CustomItem(this,7))
//            slidingDialogMenu.items.add(CustomItem(this,8))
//            slidingDialogMenu.items.add(CustomItem(this,9))
//            slidingDialogMenu.show(supportFragmentManager)

            var stringListSlidingMenue=StringListSlidingMenue()
            stringListSlidingMenue.setTitleList(arrayOf("a", "b", "c"))

            stringListSlidingMenue.show(supportFragmentManager)



        }


        items?.add(CustomItem(this,1))
        items?.add(CustomItem(this,2))
        items?.add(CustomItem(this,3))
        items?.add(CustomItem(this,4))
        items?.add(CustomItem(this,5))
        items?.add(CustomItem(this,6))
        items?.add(CustomItem(this,7))
        items?.add(CustomItem(this,8))
        items?.add(CustomItem(this,9))
        items?.add(CustomItem(this,10))

        var adapter=RecyclerViewDataItemAdapter.initializeHorrizentalRecyclerView(recycler_view,items)
//        var adapter=ViewPagerDataItemAdapter.initializeViewPager(view_pager,items)
        requestPermisions("android.permission.WRITE_EXTERNAL_STORAGE", object : PermisionUtils.PermisionGrantListener {
            override fun onPermisionGranted() {
                Toast.makeText(applicationContext, "onPermisionGranted " , Toast.LENGTH_SHORT).show()

            }

            override fun onPermisionDenied() {
                Toast.makeText(applicationContext, "onPermisionDenied " , Toast.LENGTH_SHORT).show()

            }
        })
    }
}
