package com.paraxco.formtools.CustomListItems.Fragment.CompleteProfilePages;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ScrollView;

import com.paraxco.commontools.Activities.BaseActivity;
import com.paraxco.commontools.Fragment.BaseFragment;
import com.paraxco.formtools.CustomListItems.Adapter.CompleteListItemAdapterRecyclerView;
import com.paraxco.formtools.CustomListItems.CustomListItem;
import com.paraxco.formtools.CustomListItems.Model.ProfileData;
import com.paraxco.formtools.R;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */

public abstract class CompleteProfilePage extends BaseFragment {
    ProfileData profileData;
    LinkedList<CustomListItem> hasErrorViews = new LinkedList<>();
    private CompleteListItemAdapterRecyclerView mAdapter;
    private List<CustomListItem> completeProfileItems = new LinkedList<>();
    private RecyclerView mRecyclerView;

    protected abstract void initializeItems();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.complete_profile_page, container, false);
        initializeDrawerRecyclerView(view);
        initializeItems();
        return view;
    }

    protected void addItem(CustomListItem item) {
        completeProfileItems.add(item);
    }

    protected void initializeDrawerRecyclerView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);

        // use this setting to improve performance if you know that changes
        // in content.xml do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        completeProfileItems.clear();
        hasErrorViews.clear();
        mAdapter = new CompleteListItemAdapterRecyclerView(profileData, mRecyclerView, (BaseActivity) getActivity(), completeProfileItems);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPageShow() {

    }

    @Override
    public void onPageHide() {
        super.onPageHide();
        if (mRecyclerView != null) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mRecyclerView.getWindowToken(), 0);
        }
    }

    public ProfileData getProfileData() {
        return profileData;
    }

    public CompleteProfilePage setProfileData(ProfileData profileData) {
        this.profileData = profileData;
        return this;
    }

    public boolean canLeavePage() {

        for (CustomListItem customListItem : completeProfileItems) {
            if (customListItem.isValid() > 0) {
                mAdapter.scrollTo(customListItem);
                return false;
            } else
                mAdapter.removeIndicated(customListItem);

        }
        return true;

    }

    public abstract boolean isDataValid();

    protected abstract ScrollView getScrollView();
}
