package com.paraxco.formtools.CustomListItems;


import com.paraxco.formtools.R;

/**
 *
 */

public class RadioButtonTwoLineCustomListItem extends RadioButtonCustomListItem {

    public RadioButtonTwoLineCustomListItem(String title, String radioOneTitle, String radioTwoTitle, ViewDataUpdater radioDataUpdator) {
        super(R.layout.complete_profile_radio_button_two_line_item, title, radioOneTitle, radioTwoTitle, radioDataUpdator);
    }

//    @Override
//    public int getViewType() {
//        return 6;
//    }
}
