package com.paraxco.basictools.FormTools.ListItems.CustomListItems;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.paraxco.basictools.FormTools.ListItems.CustomListItems.Listeners.CustomEditTextListener;
import com.paraxco.basictools.FormTools.ListItems.CustomListItems.Model.ProfileData;
import com.paraxco.basictools.R;


/**
 *
 */

public class PhoneWithAreaCodeCustomListItem extends CustomListItem {

    private EditText telephone;
    private TextView titleTextView;
    private String title;
    ViewDataUpdater areaCodeDataUpdater;
    ViewDataUpdater telephoneDataUpdator;
    private EditText areaCode;
    int telephonError = 0;
    int areaCodeError = 0;


    private CustomEditTextListener telephoneCustomEditTextListener = new CustomEditTextListener() {
        @Override
        public void afterTextChanged(Editable s, EditText editText, CustomListItem customListItem) {
            telephonError = telephoneDataUpdator.setData(s, getData());
        }
    };
    private final CustomEditTextListener areaCodeTextListener = new CustomEditTextListener() {
        @Override
        public void afterTextChanged(Editable s, EditText editText, CustomListItem customListItem) {
            areaCodeError = areaCodeDataUpdater.setData(s, getData());
        }
    };


    public PhoneWithAreaCodeCustomListItem(String title, ViewDataUpdater areaCodeDataUpdater, ViewDataUpdater telephoneDataUpdator) {
        super(R.layout.complete_profile_phone_area_code_item);
        areaCodeTextListener.setCustomListItem(this);
        telephoneCustomEditTextListener.setCustomListItem(this);
        this.title = title;
        this.areaCodeDataUpdater = areaCodeDataUpdater;
        this.telephoneDataUpdator = telephoneDataUpdator;
    }

//    @Override
//    public int getViewType() {
//        return 4;
//    }

    @Override
    public void initializeView(View view) {
        titleTextView = (TextView) view.findViewById(R.id.title);
        titleTextView.setText(title);
        telephone = (EditText) view.findViewById(R.id.edit_text_content);
        telephoneCustomEditTextListener.setEditText(telephone);
        setTextListener(telephone, telephoneCustomEditTextListener);

        areaCode = (EditText) view.findViewById(R.id.edit_text_area_code);
        setTextListener(areaCode, areaCodeTextListener);
        areaCodeTextListener.setEditText(areaCode);

        resetData(getData());
    }


    @Override
    public EditText requestFocus() {
        if (telephone != null)
            telephone.requestFocus();
        return null;
    }

    @Override
    public void resetData(ProfileData profileData) {
        telephone.setText((String) telephoneDataUpdator.getData(profileData));
        areaCode.setText((String) areaCodeDataUpdater.getData(profileData));
    }

    @Override
    public int isValid() {

        return (areaCodeError < 0 && telephonError < 0) ? 0 : 1;
    }
}
