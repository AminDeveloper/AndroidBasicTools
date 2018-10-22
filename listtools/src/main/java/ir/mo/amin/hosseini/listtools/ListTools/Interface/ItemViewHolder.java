package ir.mo.amin.hosseini.listtools.ListTools.Interface;

import android.view.View;

import ir.mo.amin.hosseini.listtools.ListTools.DataItem.DataItemBase;


/**
 * Created by Amin on 9/24/2017.
 */

public interface ItemViewHolder {
    View getView();

    void bindToDataItem(DataItemBase dataItem);
    void recycle();
    DataItemBase getDataItem();
    View findView(int id);

    void onShowed();

    void onHide();
}
