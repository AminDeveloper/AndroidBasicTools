package com.paraxco.formtools.CustomListItems.Listeners;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.paraxco.formtools.CustomListItems.CustomListItem;


/**
 *
 */

public abstract class CustomEditTextListener extends completeListListener implements TextWatcher {
    EditText editText;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        afterTextChanged(s, editText, customListItem);

    }

    public abstract void afterTextChanged(Editable s, EditText editText, CustomListItem customListItem);

    public void setEditText(EditText editText) {
        this.editText = editText;
    }
}
