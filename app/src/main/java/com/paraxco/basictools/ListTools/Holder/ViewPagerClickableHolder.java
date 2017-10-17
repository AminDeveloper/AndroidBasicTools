package com.paraxco.basictools.ListTools.Holder;

import android.view.View;

import com.paraxco.basictools.ListTools.Adapter.ViewPager.ViewPagerAdapter;
import com.paraxco.basictools.ListTools.DataItem.DataItemBase;
import com.paraxco.basictools.ListTools.Interface.ItemViewHolder;


/**
 *
 */

public class ViewPagerClickableHolder extends ViewPagerAdapter.ViewHolder implements ItemViewHolder {
    HolderHelper holderHelper = new HolderHelper();

    public ViewPagerClickableHolder(View itemView) {
        super(itemView);
    }


    public View getView() {
        return itemView;
    }

    @Override
    public void bindToDataItem(DataItemBase dataItem) {
        holderHelper.bindToDataItem(this, dataItem);
    }

    @Override
    public void recycle() {
        holderHelper.recycle();
    }

    @Override
    public DataItemBase getDataItem() {
        return holderHelper.dataItem;
    }
    @Override
    public View findView(int id) {
        return holderHelper.findView(id,getView());
    }

}

