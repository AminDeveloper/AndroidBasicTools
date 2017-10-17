package com.paraxco.basictools.FormTools.ListItems.CustomListItems.Adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;

import com.paraxco.basictools.Activities.BaseActivity;
import com.paraxco.basictools.FormTools.ListItems.CustomListItems.CustomListItem;
import com.paraxco.basictools.FormTools.ListItems.CustomListItems.Model.ProfileData;
import com.paraxco.basictools.ListTools.Adapter.RecyclerView.RecyclerViewDataItemAdapter;
import com.paraxco.basictools.ListTools.Holder.RecyclerViewClickableHolder;
import com.paraxco.basictools.R;

import java.util.List;


/**
 *
 */

public class CompleteListItemAdapterRecyclerView extends RecyclerViewDataItemAdapter<CustomListItem> {
    private final RecyclerView recyclerView;
    private final ProfileData profileData;
    BaseActivity activity;
    private CustomListItem indicatedItem;

    public CompleteListItemAdapterRecyclerView(ProfileData profileData, RecyclerView recyclerView, BaseActivity activity, List<CustomListItem> customListItems) {
        super(recyclerView, customListItems);
        this.profileData = profileData;
        this.recyclerView = recyclerView;
        this.activity = activity;
    }

    @Override
    public void onBindViewHolder(RecyclerViewClickableHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder.getDataItem().equals(indicatedItem)) {
            holder.getView().setBackground(ContextCompat.getDrawable(activity, R.drawable.boarder));
        } else
            holder.getView().setBackground(null);
//            holder.item.getView().setBackgroundColor(Color.WHITE);
//            holder.item.getView().setBackground(ContextCompat.getDrawable(activity, R.drawable.child_background));
    }


    public void scrollTo(final CustomListItem customListItem) {
        indicatedItem = customListItem;

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
//                notifyItemChanged(compeleteListItems.indexOf(customListItem));
                notifyDataSetChanged();
                recyclerView.scrollToPosition(getIndexOf(customListItem));

//                if (focusedEditText != null)
//                    focusedEditText.clearFocus();
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        customListItem.requestFocus();
                    }
                });
            }
        });
    }

    public void removeIndicated(final CustomListItem customListItem) {
        if (indicatedItem != null && indicatedItem.equals(customListItem)) {
            indicatedItem = null;
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    notifyItemChanged(getIndexOf(customListItem));
                }
            });
        }
    }
}


