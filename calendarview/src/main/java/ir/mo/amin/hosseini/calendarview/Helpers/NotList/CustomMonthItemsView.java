package ir.mo.amin.hosseini.calendarview.Helpers.NotList;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import ir.mo.amin.hosseini.calendarview.R;


public class CustomMonthItemsView extends LinearLayout {

    public CustomMonthItemsView(Context context) {
        super(context);
        inflate(context,R.layout.month_item_list,this);
    }

    public CustomMonthItemsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.month_item_list,this);
    }

    public CustomMonthItemsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context,R.layout.month_item_list,this);
    }
   public void initView(){


    }

}
