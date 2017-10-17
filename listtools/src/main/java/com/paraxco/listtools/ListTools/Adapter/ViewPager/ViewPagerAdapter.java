package com.paraxco.listtools.ListTools.Adapter.ViewPager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


import com.paraxco.basictools.ViewPagerTools.Adapter.RecyclerPagerAdapter;
import com.paraxco.listtools.ListTools.Adapter.AdapterHelper;
import com.paraxco.listtools.ListTools.DataItem.DataItemBase;
import com.paraxco.listtools.ListTools.Holder.ViewPagerClickableHolder;
import com.paraxco.listtools.ListTools.Interface.ItemViewHolder;

import java.util.List;

/**
 * Created by Amin on 9/24/2017.
 */

public abstract class ViewPagerAdapter <DATA_ITEM_TYPE extends DataItemBase, VH extends RecyclerPagerAdapter.ViewHolder> extends RecyclerPagerAdapter<VH> {
    AdapterHelper<DATA_ITEM_TYPE> adapterHelper;

    public ViewPagerAdapter(Context context, List<DATA_ITEM_TYPE> items) {
        adapterHelper = new AdapterHelper<>(context, items);
    }

    @Override
    public int getItemViewType(int position) {

        return adapterHelper.getItemViewType(position);
    }

    public int getTypeResource(int viewType) {
        return adapterHelper.getTypeResource(viewType);
    }


    @Override
    public VH onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = adapterHelper.getView(viewGroup, viewType);
        VH viewHolder = getViewHolder(view);
        return viewHolder;
    }


    public void setItems(List<DATA_ITEM_TYPE> items) {
        adapterHelper.setItems(items);
    }

    protected  VH getViewHolder(View view){
        return (VH) new ViewPagerClickableHolder(view);
    }


    @Override
    public void onBindViewHolder(VH holder, int position) {
        adapterHelper.bindToViewHolder((ItemViewHolder) holder,position );
    }

    @Override
    public void onDestroyViewHolder(VH holder, int position) {
        super.onDestroyViewHolder(holder, position);
        adapterHelper.recycleViewHolder((ItemViewHolder) holder);
    }

    @Override
    public int getItemCount() {
        return adapterHelper.getItemCount();
    }

    protected int getIndexOf(DATA_ITEM_TYPE dataItem) {
        return adapterHelper.getIndexOf(dataItem);
    }

    public void clearItems() {
        adapterHelper.clearItems();
        notifyDataSetChanged();
    }
}
