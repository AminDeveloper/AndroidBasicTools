package com.paraxco.basictools.ListTools.Model;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.paraxco.basictools.R;
import com.paraxco.listtools.ListTools.DataItem.DataItemBase;

/**
 * Created by Amin on 11/6/2017.
 */

public class CustomItem extends DataItemBase {
    int i = 0;
    Context mcontext;

    public CustomItem(Context context, int i) {
        super(R.layout.item_layout);
        this.i = i;
        this.mcontext = context;
    }

    @Override
    public void initializeView(View view) {
        TextView title = (TextView) findView(R.id.title);
//        Toast.makeText(mcontext,"initializeView "+i,Toast.LENGTH_SHORT).show();
        title.setText(String.valueOf(i));
    }

    @Override
    public void releaseView() {
        super.releaseView();
//        Toast.makeText(mcontext,"releaseView "+i,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHideItem() {
        super.onHideItem();
        Toast.makeText(mcontext, "onHideItem " + i, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onShowItem() {
        super.onShowItem();
        Toast.makeText(mcontext, "onShowItem " + i, Toast.LENGTH_SHORT).show();

    }
}
