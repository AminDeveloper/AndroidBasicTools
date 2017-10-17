package com.paraxco.basictools.ListTools.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paraxco.basictools.ListTools.DataItem.DataItemBase;
import com.paraxco.basictools.ListTools.Interface.ItemViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Amin on 9/24/2017.
 */

public class AdapterHelper<DATA_ITEM_TYPE extends DataItemBase> {

    protected List<DATA_ITEM_TYPE> items;
    protected Context context;
    private Map<Integer, Integer> resourceTypes = new HashMap<>();
    private Map<String, Integer> classType = new HashMap<>();

    public AdapterHelper(Context context, List<DATA_ITEM_TYPE> items) {
        this.items = items;
        this.context = context;
    }

    public int getItemViewType(int position) {

        DataItemBase dataItem = items.get(position);
        //register layoutResourceID in layout types for this class name
        synchronized (classType) {
            if (!classType.containsKey(dataItem.getClass().getName())) {
                int type = classType.size();
                classType.put(dataItem.getClass().getName(), type);
                resourceTypes.put(type, dataItem.getLayoutResourceID());
            }
        }
        return getViewType(dataItem);
    }

    public int getTypeResource(int viewType) {
        return resourceTypes.get(viewType);
    }

    /**
     * gets type for resource to inflate
     *
     * @return
     */
    public int getViewType(DataItemBase dataItem) {
        return classType.get(dataItem.getClass().getName());
    }

    public View getView(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(getTypeResource(viewType), viewGroup, false);
        return view;
    }

    public void setItems(List<DATA_ITEM_TYPE> items) {
        this.items = items;
    }


    public DATA_ITEM_TYPE getItem(int position) {
        return items.get(position);
    }

    public int getItemCount() {
        return (null != items ? items.size() : 0);
    }

    public int getIndexOf(DATA_ITEM_TYPE dataItem) {
        if (items != null)
            return items.indexOf(dataItem);
        return -1;
    }
    public void bindToViewHolder(ItemViewHolder holder,int position ) {
        DATA_ITEM_TYPE dataItem = getItem(position);
        holder.bindToDataItem(dataItem);
    }
    public <VH extends RecyclerView.ViewHolder> void recycleViewHolder(ItemViewHolder holder) {
        holder.recycle();

    }
    public <VH extends RecyclerView.ViewHolder> void clearItems() {
        items.clear();
    }


}
