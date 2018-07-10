package com.paraxco.basictools.ListTools.Model;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.paraxco.basictools.R;
import com.paraxco.commontools.Utils.SmartLogger;
import com.paraxco.listtools.ListTools.DataItem.DataItemBase;

/**
 * Created by Amin on 11/6/2017.
 */
public class CustomItem extends DataItemBase {
    private int parentIndex;
    int i = 0;
    Context mcontext;

    public CustomItem(Context context, int i) {
        super(R.layout.item_layout);
        this.i = i;
        this.mcontext = context;
    }
    public CustomItem(Context context, int i,int parentIndex) {
        super(R.layout.item_layout);
        this.i = i;
        this.parentIndex=parentIndex;
        this.mcontext = context;
    }

    @Override
    public void initializeView(View view) {
        TextView title = (TextView) findView(R.id.title);
//      Toast.makeText(mcontext,"initializeView "+i,Toast.LENGTH_SHORT).show();
        title.setText(String.valueOf(i));
        SmartLogger.logDebug("item :"+parentIndex+","+i);
    }

    @Override
    public void releaseView() {
        super.releaseView();
//        Toast.makeText(mcontext,"releaseView "+i,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHideItem() {
        super.onHideItem();
//        Toast.makeText(mcontext, "onHideItem " + i, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onShowItem() {
        super.onShowItem();
//        Toast.makeText(mcontext, "onShowItem " + i, Toast.LENGTH_SHORT).show();

    }
}
