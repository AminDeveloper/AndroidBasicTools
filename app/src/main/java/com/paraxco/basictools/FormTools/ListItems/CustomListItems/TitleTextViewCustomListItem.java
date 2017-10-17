package com.paraxco.basictools.FormTools.ListItems.CustomListItems;

import android.view.View;
import android.widget.TextView;

import com.paraxco.basictools.R;


/**
 *
 */
public abstract class TitleTextViewCustomListItem extends CustomListItem {
    private TextView titleTextView;
    private String title;

    public TitleTextViewCustomListItem(int layoutResourceID, String title) {
        super(layoutResourceID);
        this.title = title;
    }

    @Override
    public void initializeView(View view) {
        titleTextView = (TextView) view.findViewById(R.id.title);
        titleTextView.setText(title);
    }
}
