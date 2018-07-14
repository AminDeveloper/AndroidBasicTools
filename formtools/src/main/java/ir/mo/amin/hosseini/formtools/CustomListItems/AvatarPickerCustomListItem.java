package ir.mo.amin.hosseini.formtools.CustomListItems;

import android.view.View;
import android.widget.EditText;
import ir.mo.amin.hosseini.formtools.CustomListItems.Model.ProfileData;
import ir.mo.amin.hosseini.formtools.R;

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
