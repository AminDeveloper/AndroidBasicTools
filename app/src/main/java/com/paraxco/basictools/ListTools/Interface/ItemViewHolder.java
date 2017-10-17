package com.paraxco.basictools.ListTools.Interface;

import android.view.View;

import com.paraxco.basictools.ListTools.DataItem.DataItemBase;

/**
 * Created by Amin on 9/24/2017.
 */

public interface ItemViewHolder {
    View getView();

    void bindToDataItem(DataItemBase dataItem);
    void recycle();
    DataItemBase getDataItem();
    View findView(int id);
}
