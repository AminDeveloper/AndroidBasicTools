package com.paraxco.basictools.FormTools.ListItems.CustomListItems;

import android.view.View;
import android.widget.EditText;
import com.paraxco.basictools.FormTools.ListItems.CustomListItems.Model.ProfileData;
import com.paraxco.basictools.R;

/**
 *
 */

public class AvatarPickerCustomListItem extends CustomListItem {

    public AvatarPickerCustomListItem() {
        super(R.layout.complete_profile_avatar_item);
    }

//    @Override
//    public int getViewType() {
//        return 5;
//    }

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
