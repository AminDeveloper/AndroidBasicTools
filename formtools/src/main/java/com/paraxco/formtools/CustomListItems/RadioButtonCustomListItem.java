package com.paraxco.formtools.CustomListItems;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.paraxco.formtools.CustomListItems.Listeners.RadioButtonCompleteProfileItemListener;
import com.paraxco.formtools.CustomListItems.Model.ProfileData;
import com.paraxco.formtools.R;


/**
 *
 */

public class RadioButtonCustomListItem extends CustomListItem {
    private TextView titleTextView;
    private String title;
    private String radioOneTitle;
    private RadioButton radioOne;
    private String radioTwoTitle;
    private RadioButton radioTwo;

    ViewDataUpdater radioDataUpdator;
    RadioButtonCompleteProfileItemListener radioButtonCompleteProfileItemListener = new RadioButtonCompleteProfileItemListener() {
        @Override
        public void onCheckedChanged(RadioButton buttonView, int radioButtonNumber) {
            radioDataUpdator.setData(radioButtonNumber, getData());
        }
    };


    public RadioButtonCustomListItem(String title, String radioOneTitle, String radioTwoTitle, ViewDataUpdater radioDataUpdator) {
        super(R.layout.complete_profile_radio_button_item);
        this.title = title;
        this.radioOneTitle = radioOneTitle;
        this.radioTwoTitle = radioTwoTitle;
        radioButtonCompleteProfileItemListener.setCustomListItem(this);
        this.radioDataUpdator = radioDataUpdator;
    }

    public RadioButtonCustomListItem(int layoutResourceID, String title, String radioOneTitle, String radioTwoTitle, ViewDataUpdater radioDataUpdator) {
        super(layoutResourceID);
        this.title = title;
        this.radioOneTitle = radioOneTitle;
        this.radioTwoTitle = radioTwoTitle;
        radioButtonCompleteProfileItemListener.setCustomListItem(this);
        this.radioDataUpdator = radioDataUpdator;
    }

//    @Override
//    public int getViewType() {
//        return 3;
//    }

    @Override
    public void initializeView(View view) {
        titleTextView = (TextView) view.findViewById(R.id.title);
        titleTextView.setText(title);

        radioOne = (RadioButton) view.findViewById(R.id.radio_1);
        radioTwo = (RadioButton) view.findViewById(R.id.radio_2);

        resetData(getData());

        radioOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    radioButtonCompleteProfileItemListener.onCheckedChanged(radioOne, 1);
            }
        });

        radioTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    radioButtonCompleteProfileItemListener.onCheckedChanged(radioTwo, 2);
            }
        });

        radioOne.setText(radioOneTitle);
        radioTwo.setText(radioTwoTitle);


    }

    @Override
    public EditText requestFocus() {
        return null;

    }

    @Override
    public void resetData(ProfileData profileData) {
        int radoSelectedNumber = (int) radioDataUpdator.getData(profileData);
        if (radoSelectedNumber == 1) {
            radioTwo.setChecked(false);
            radioOne.setChecked(true);
        } else {
            radioOne.setChecked(false);

            radioTwo.setChecked(true);
        }


    }


}
