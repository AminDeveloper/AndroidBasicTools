package ir.mo.amin.hosseini.listtools.ListTools.Holder;

import android.view.View;

import ir.mo.amin.hosseini.listtools.ListTools.Interface.ListItemClickListener;
import ir.mo.amin.hosseini.listtools.ListTools.DataItem.DataItemBase;
import ir.mo.amin.hosseini.listtools.ListTools.Interface.ItemViewHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amin on 9/26/2017.
 */

public class HolderHelper {

    DataItemBase dataItem;
    private Map<Integer, View> foundView = new HashMap<>();


    private void setClickListener(View itemView, final ListItemClickListener clickListener) {
//            View root=itemView.findViewById(R.id.root);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null)
                    clickListener.onSelected(dataItem);
            }
        });
    }

    public void bindToDataItem(ItemViewHolder itemViewHolder, DataItemBase dataItem) {
        this.dataItem = dataItem;
        setClickListener(itemViewHolder.getView(), dataItem.getClickListener());
        this.dataItem.setHolder(itemViewHolder);
    }


    public void recycle() {
        this.dataItem.releaseView();
    }
    public void showed(){
        this.dataItem.onShowItem();
    }
    public void hide(){
        this.dataItem.onHideItem();
    }

    public View findView(int id, View itemView) {
        if (foundView.containsKey(id))
            return foundView.get(id);
        else {
            View found = itemView.findViewById(id);
            foundView.put(id, found);
            return found;
        }
    }
}
