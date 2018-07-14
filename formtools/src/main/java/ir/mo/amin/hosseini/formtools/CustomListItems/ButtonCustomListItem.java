package ir.mo.amin.hosseini.formtools.CustomListItems;

import android.view.View;
import android.widget.EditText;
import ir.mo.amin.hosseini.formtools.CustomListItems.Model.ProfileData;
import ir.mo.amin.hosseini.formtools.R;


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
