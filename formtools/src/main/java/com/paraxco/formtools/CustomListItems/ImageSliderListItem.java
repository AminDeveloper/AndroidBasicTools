package com.paraxco.formtools.CustomListItems;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;


import com.paraxco.formtools.CustomListItems.Model.ProfileData;
import com.paraxco.formtools.R;
import com.paraxco.listtools.ListTools.Adapter.RecyclerView.RecyclerViewDataItemAdapter;
import com.paraxco.listtools.ListTools.DataItem.DataItemBase;

import java.util.List;

/**
 *
 */

public class ImageSliderListItem extends CustomListItem {
    RecyclerView imageList;
    private List<? extends DataItemBase> imageItems;
    private RecyclerViewDataItemAdapter adapter;

    public ImageSliderListItem() {
        super(R.layout.image_slider);
    }

    @Override
    public void initializeView(View view) {
        imageList = (RecyclerView) view.findViewById(R.id.list);
        adapter = RecyclerViewDataItemAdapter.initializeHorrizentalRecyclerView(imageList, imageItems);


    }

    @Override
    public EditText requestFocus() {
        return null;
    }

    @Override
    public void resetData(ProfileData profileData) {

    }
}
