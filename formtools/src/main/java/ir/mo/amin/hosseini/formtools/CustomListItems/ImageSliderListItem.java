package ir.mo.amin.hosseini.formtools.CustomListItems;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;


import ir.mo.amin.hosseini.formtools.CustomListItems.Model.ProfileData;
import ir.mo.amin.hosseini.formtools.R;
import ir.mo.amin.hosseini.listtools.ListTools.Adapter.RecyclerView.RecyclerViewDataItemAdapter;
import ir.mo.amin.hosseini.listtools.ListTools.DataItem.DataItemBase;

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
