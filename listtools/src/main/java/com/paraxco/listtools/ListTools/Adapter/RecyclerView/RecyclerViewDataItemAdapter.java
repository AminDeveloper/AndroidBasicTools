package com.paraxco.listtools.ListTools.Adapter.RecyclerView;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.paraxco.listtools.ListTools.DataItem.DataItemBase;
import com.paraxco.listtools.ListTools.Holder.RecyclerViewClickableHolder;

import java.util.List;

/**
 *
 */

public class RecyclerViewDataItemAdapter<DATA_ITEM_TYPE extends DataItemBase> extends RecyclerViewAdapter<DATA_ITEM_TYPE, RecyclerViewClickableHolder> {

    private final RecyclerView recyclerView;

    public RecyclerViewDataItemAdapter(RecyclerView recyclerView, List<DATA_ITEM_TYPE> items) {
        super(recyclerView.getContext(), items);
        this.recyclerView = recyclerView;
        this.recyclerView.setAdapter(this);
    }


    public static RecyclerViewDataItemAdapter initializeLinearRecyclerView(RecyclerView mRecyclerView, List<? extends DataItemBase> items) {

        // use this setting to improve performance if you know that changes
        // in content.xml do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        RecyclerViewDataItemAdapter mAdapter = new RecyclerViewDataItemAdapter<>(mRecyclerView, items);


        mAdapter.notifyDataSetChanged();
        return mAdapter;
    }

    public static RecyclerViewDataItemAdapter initializeHorrizentalRecyclerView(RecyclerView mRecyclerView, List<? extends DataItemBase> items) {

        // use this setting to improve performance if you know that changes
        // in content.xml do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager
                = new LinearLayoutManager(mRecyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        RecyclerViewDataItemAdapter mAdapter = new RecyclerViewDataItemAdapter<>(mRecyclerView, items);


        mAdapter.notifyDataSetChanged();
        return mAdapter;

    }

    public static RecyclerViewDataItemAdapter initializeGridRecyclerView(RecyclerView mRecyclerView, int spanCount, List<? extends DataItemBase> items) {

        // use this setting to improve performance if you know that changes
        // in content.xml do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        GridLayoutManager mLayoutManager = new GridLayoutManager(mRecyclerView.getContext(), spanCount);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        RecyclerViewDataItemAdapter mAdapter = new RecyclerViewDataItemAdapter<>(mRecyclerView, items);


        mAdapter.notifyDataSetChanged();
        return mAdapter;
    }
//    public static RecyclerViewDataItemAdapter initializeEndlessViewPager(ViewPager viewPager) {
//
//
//
//        // specify an adapter (see also next example)
//        RecyclerViewDataItemAdapter mAdapter = new RecyclerViewDataItemAdapter<>(mRecyclerView, items);
//
//
//        mAdapter.onNotifyDataSetChanged();
//        return mAdapter;
//    }

}

