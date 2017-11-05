package com.paraxco.calendarview.Fragments.CalendarFragments

import android.view.View
import android.widget.TextView
import com.paraxco.listtools.ListTools.Dialog.SlidingDialogMenu
import com.paraxco.listtools.R.id.menu_item

/**
 * Created by Amin on 8/12/2017.
 */

class ListSlidingMenue : SlidingDialogMenu() {
    var selectListener: ItemSelectedListener? = null
    private var titleList: List<String>? = null

    override fun fillItems() {

        titleList?.forEach {
            items.add(object : slidingMenuItem<Any>() {
                override fun initializeView(view: View?) {
                    var textView=view!!.findViewById<TextView>(menu_item)
                    textView.text = it
                    view.setOnClickListener(View.OnClickListener { viwe: View? ->
                        selectListener?.onItemSelected(titleList!!.indexOf(it))
                        dialog.dismiss()

                    })
                }
            })
        }

//        items.add(object : slidingMenuItem<Any>() {
//            override fun initializeView(view: View?) {
//                view!!.menu_item.setText(R.string.calendar_month)
//                view.setOnClickListener(View.OnClickListener { viwe: View? ->
//                    selectListener?.onItemSelected(1)
//                    dialog.dismiss()
//
//                })
//            }
//        })
//        items.add(object : slidingMenuItem<Any>() {
//            override fun initializeView(view: View?) {
//                view!!.menu_item.setText(R.string.calendar_day)
//                view.setOnClickListener(View.OnClickListener { viwe: View? ->
//                    selectListener?.onItemSelected(2)
//                   dialog.dismiss()
//                })
//            }
//        })
//        items.add(object : slidingMenuItem<Any>() {
//            override fun initializeView(view: View?) {
//                view!!.menu_item.setText(" gozine 3")
//
//            }
//
//        })
    }

    public fun setTitleList(titles: List<String>) {

        titleList = titles
    }


    interface ItemSelectedListener {
        fun onItemSelected(i: Int)

    }

}
