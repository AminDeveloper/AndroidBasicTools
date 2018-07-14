package ir.mo.amin.hosseini.basictools.ListTools.Model

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import ir.mo.amin.hosseini.basictools.R
import ir.mo.amin.hosseini.listtools.ListTools.Adapter.RecyclerView.RecyclerViewDataItemAdapter
import ir.mo.amin.hosseini.listtools.ListTools.DataItem.DataItemBase

class RecyclerDataItem(val context: Context,var index:Int) : DataItemBase<Any>(R.layout.recycler_item) {

    private val items = mutableListOf<DataItemBase<Any>>()

    override fun initializeView(view: View?) {

        for (i in 1..100)
            items?.add(CustomItem(context, i,index))

        val recyclerView = findView(R.id.recycler_view) as RecyclerView?
        var adapter = RecyclerViewDataItemAdapter.initializeHorrizentalRecyclerView(recyclerView, items)
    }
}
