package com.paraxco.basictools.FormTools.ListItems.CustomListItems;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;


import com.paraxco.basictools.FormTools.ListItems.CustomListItems.Model.ProfileData;
import com.paraxco.basictools.ListTools.Adapter.RecyclerView.RecyclerViewDataItemAdapter;
import com.paraxco.basictools.ListTools.DataItem.DataItemBase;
import com.paraxco.basictools.R;

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
