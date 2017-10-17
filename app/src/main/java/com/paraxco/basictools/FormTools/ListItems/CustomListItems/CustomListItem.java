package com.paraxco.basictools.FormTools.ListItems.CustomListItems;

import android.text.TextWatcher;
import android.widget.EditText;

import com.paraxco.basictools.FormTools.ListItems.CustomListItems.Listeners.CustomEditTextListener;
import com.paraxco.basictools.FormTools.ListItems.CustomListItems.Model.ProfileData;
import com.paraxco.basictools.ListTools.DataItem.DataItemBase;
import com.paraxco.basictools.R;


/**
 *
 */

public abstract class CustomListItem extends DataItemBase<ProfileData> {
    int errorRes = 0;

    public CustomListItem(int layoutResourceID) {
        super(layoutResourceID);
    }

    protected static void setTextListener(EditText editText, CustomEditTextListener customEditTextListener) {
        //when recycler view recycles text view last listeners should be removed
        Object tag;
        if ((tag = editText.getTag(R.attr.editTextStyle)) != null) {
            editText.removeTextChangedListener((TextWatcher) tag);
        }
        editText.setTag(R.attr.editTextStyle, customEditTextListener);
        editText.addTextChangedListener(customEditTextListener);
    }

    public CustomListItem setProfileData(ProfileData profileData) {
        setData(profileData);
        return this;
    }

//    public static int getTypeResource(int viewType) {
//        switch (viewType) {
//            case 1:
//                return R.layout.complete_profile_textview_item;
//            case 2:
//                return R.layout.complete_profile_spinner_item;
//            case 3:
//                return R.layout.complete_profile_radio_button_item;
//            case 4:
//                return R.layout.complete_profile_phone_area_code_item;
//            case 5:
//                return R.layout.complete_profile_avatar_item;
//            case 6:
//                return R.layout.complete_profile_radio_button_two_line_item;
//            case 7:
//                return R.layout.complete_profile_date_time_picker;
//        }
//        return 0;
//    }

    /**
     * request focus for item ie:EditText
     *
     * @return focused view
     */
    public abstract EditText requestFocus();

    public abstract void resetData(ProfileData profileData);

    public int isValid() {
        return errorRes;
    }

    public interface ViewDataUpdater {
        Object getData(ProfileData profileData);

        int setData(Object value, ProfileData profileData);
    }
}
