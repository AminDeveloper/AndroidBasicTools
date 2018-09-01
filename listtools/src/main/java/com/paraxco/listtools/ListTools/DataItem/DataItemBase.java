package com.paraxco.listtools.ListTools.DataItem;

import android.view.View;

import com.paraxco.listtools.ListTools.Interface.ListItemClickListener;
import com.paraxco.listtools.ListTools.Interface.ItemViewHolder;


/**
 * item of a row wich contains Data needed for it
 */

public abstract class DataItemBase<DATA_TYPE> {
    DATA_TYPE data;
    ItemViewHolder holder;

    private ListItemClickListener clickListener;
    int layoutResourceID;

    public DataItemBase(int layoutResourceID) {
        this.layoutResourceID = layoutResourceID;
    }

    /**
     * initialize inflated view
     *
     * @param view
     */
    public abstract void initializeView(View view);

    public void releaseView(){

    }
    public void onShowItem(){

    }
    public void onHideItem(){

    }

    public View getView() {
        if (holder != null)
            return holder.getView();
        else
            return null;
    }

    public View findView(int id) {
        return holder.findView(id);

    }

    public ListItemClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(ListItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setLongClickListener(ListItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public DATA_TYPE getData() {
        return data;
    }

    public void setData(DATA_TYPE data) {
        this.data = data;
    }

    public ItemViewHolder getHolder() {
        return holder;
    }

    public void setHolder(ItemViewHolder holder) {
        this.holder = holder;
        initializeView(holder.getView());
    }

    public int getLayoutResourceID() {
        return layoutResourceID;
    }
}
