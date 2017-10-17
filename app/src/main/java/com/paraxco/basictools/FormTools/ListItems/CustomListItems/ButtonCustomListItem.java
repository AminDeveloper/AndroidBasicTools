package com.paraxco.basictools.FormTools.ListItems.CustomListItems;

import android.view.View;
import android.widget.EditText;
import com.paraxco.basictools.FormTools.ListItems.CustomListItems.Model.ProfileData;
import com.paraxco.basictools.R;


/**
 * Created by Amin on 7/30/2017.
 */

public class ButtonCustomListItem extends CustomListItem {
    public ButtonCustomListItem() {
        super(R.layout.button_item);
    }

    @Override
    public void initializeView(View view) {

    }

    @Override
    public EditText requestFocus() {
        return null;
    }

    @Override
    public void resetData(ProfileData profileData) {

    }
}
