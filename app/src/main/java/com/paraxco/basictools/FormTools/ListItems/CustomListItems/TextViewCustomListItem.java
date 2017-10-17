package com.paraxco.basictools.FormTools.ListItems.CustomListItems;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.paraxco.basictools.FormTools.ListItems.CustomListItems.Listeners.CustomEditTextListener;
import com.paraxco.basictools.FormTools.ListItems.CustomListItems.Model.ProfileData;
import com.paraxco.basictools.R;


/**
 *
 */
public class TextViewCustomListItem extends TitleTextViewCustomListItem {
    private EditText editText;
    ViewDataUpdater textDataUpdater;
    int inputType = InputType.TYPE_CLASS_TEXT;

    CustomEditTextListener customEditTextListener = new CustomEditTextListener() {
        @Override
        public void afterTextChanged(Editable s, EditText editText, CustomListItem customListItem) {
            showTextError(textDataUpdater.setData(s, getData()), editText, customListItem);
        }
    };

    public TextViewCustomListItem(String title, ViewDataUpdater textDataUpdater) {
        super(R.layout.complete_profile_textview_item, title);
        this.customEditTextListener.setCustomListItem(this);
        this.textDataUpdater = textDataUpdater;
    }

    public TextViewCustomListItem(String title, ViewDataUpdater textDataUpdater, int inputType) {
        super(R.layout.complete_profile_textview_item, title);
        this.customEditTextListener.setCustomListItem(this);
        this.textDataUpdater = textDataUpdater;
        this.inputType = inputType;
    }

//    @Override
//    public int getViewType() {
//        return 1;
//    }

    @Override
    public void initializeView(View view) {
        super.initializeView(view);
        editText = (EditText) view.findViewById(R.id.edit_text_content);
        customEditTextListener.setEditText(editText);
        setTextListener(editText, customEditTextListener);
        editText.setInputType(inputType);
        if (inputType == InputType.TYPE_CLASS_NUMBER)
            editText.setTextDirection(View.TEXT_DIRECTION_LTR);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
//                    InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.SHOW_IMPLICIT);
//                    editText.selectAll();
                    if (editText.length() > 0)
                        editText.setSelection(editText.getText().length());
                }
            }
        });
        resetData(getData());
    }


    private String getTextData(ProfileData profileData) {
        String text = (String) textDataUpdater.getData(profileData);
        if (text == null)
            return "";
        else
            return text;
    }

    @Override
    public EditText requestFocus() {
//        ((InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
//        if (focusedEditText != null)
//            focusedEditText.clearFocus();
        if (editText != null) {
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
//            ((InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
//        editText.selectAll();
        return editText;

//    }
//                ) {

//             editText .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//            InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//            inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
//        }
    }

    @Override
    public void resetData(ProfileData profileData) {
        editText.setText(getTextData(profileData));
    }


    protected void showTextError(int resultRes, EditText editText, CustomListItem customListItem) {
        errorRes = resultRes;
        if (resultRes > 0) {

//            hasErrorViews.add(customListItem);
//            if (editText.getTag() != null) {

//            editText.setError(editText.getContext().getString(resultRes));
//            }
        } else {
//            editText.setError(null);

//            hasErrorViews.remove(customListItem);
//            mAdapter.removeIndicated(customListItem);
        }
//        editText.setTag(Tag.NOT_FIRST_TIME);
    }
}
