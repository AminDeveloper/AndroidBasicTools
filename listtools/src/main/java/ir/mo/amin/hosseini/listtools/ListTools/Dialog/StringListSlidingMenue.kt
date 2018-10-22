package ir.mo.amin.hosseini.calendarview.Fragments.CalendarFragments

import android.view.View
import android.widget.TextView
import ir.mo.amin.hosseini.listtools.ListTools.DataItem.DataItemBase
import ir.mo.amin.hosseini.listtools.ListTools.Dialog.SlidingDialogMenu
import ir.mo.amin.hosseini.listtools.R.id.menu_item
import ir.mo.amin.hosseini.listtools.R.layout.sliding_menu_item

/**
 * Created by Amin on 8/12/2017.
 */

class StringListSlidingMenue : SlidingDialogMenu() {
    var selectListener: ItemSelectedListener? = null
    private var titleList: List<String>? = null

    override fun fillItems() {

//        titleList?.forEach {
//            items.add(object : slidingMenuItem<Any>() {
//                override fun initializeView(view: View?) {
//                    var textView=view!!.findViewById<TextView>(menu_item)
//                    textView.text = it
//                    view.setOnClickListener(View.OnClickListener { viwe: View? ->
//                        selectListener?.onItemSelected(titleList!!.indexOf(it))
//                        dialog.dismiss()
//
//                    })
//                }
//            })
//        }

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

    public fun setTitleList(titles: List<String>, textSize: Float?=null) {

        titleList = titles
        titleList?.forEach {
            items.add(object : slidingMenuItem<Any>() {
                override fun initializeView(view: View?) {
                    var textView = view!!.findViewById<TextView>(menu_item)
                    textView.text = it
                    if (textSize != null)
                        textView.textSize = textSize
                    view.setOnClickListener(View.OnClickListener { viwe: View? ->
                        selectListener?.onItemSelected(titleList!!.indexOf(it))
                        dialog.dismiss()

                    })
                }
            })
        }
    }

    public fun setListItems(titles: List<DataItemBase<Any>>) {

        items.clear()
        items.addAll(titles)

    }


    public fun setTitleList(titles: Array<String>) {
        setTitleList(titles.asList())
    }

    interface ItemSelectedListener {
        fun onItemSelected(i: Int)

    }

    abstract class slidingMenuItem<T> : DataItemBase<T>(sliding_menu_item) {
////        override fun initializeView(view: View?) {
////
////        }
    }

}
