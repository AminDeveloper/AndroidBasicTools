package com.paraxco.formtools.CustomListItems.Listeners;

import android.view.View;
import android.widget.AdapterView;

import com.paraxco.formtools.CustomListItems.Fragment.CompleteProfilePages.CompleteProfilePage;


/**
 *
 */

public abstract class CustomSelectItemListener extends completeListListener implements AdapterView.OnItemSelectedListener {
    CompleteProfilePage completeProfilePage;
    private boolean firstTime = true;

    public CustomSelectItemListener(CompleteProfilePage completeProfilePage) {
        this.completeProfilePage = completeProfilePage;
    }

    public CustomSelectItemListener() {
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        boolean b = completeProfilePage instanceof PersonalPage;
//        if (b)
//            Log.d("SPNT", "inside onItemSelected completeProfilePage.class = " + completeProfilePage.getClass());
//        if (b)
//            Log.d("SPNT", "inside onItemSelected firstTime = " + firstTime + " position = " + position);
//        if (firstTime && position == 0) {//initial stae set by default not data change is needed
//            view.setTag(Tag.NOT_FIRST_TIME);
//            if (b)
//                Log.d("SPNT", "inside onItemSelected skeeping");

//            firstTime = false;
//        } else {
//            if (b)
//                Log.d("SPNT", "inside onItemSelected setting data");

        setData(parent, view, position, id);
//        }
//        completeProfilePage.showSpinnerError((Spinner) parent, position, id,customListItem);
    }

    protected abstract void setData(AdapterView<?> parent, View view, int position, long id);

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
