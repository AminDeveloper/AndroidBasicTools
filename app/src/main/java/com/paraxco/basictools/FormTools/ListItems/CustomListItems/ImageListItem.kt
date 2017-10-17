package com.paraxco.app63167.Helpers.ListItems.CustomListItems

import android.view.View
import android.widget.EditText
import com.paraxco.basictools.FormTools.ListItems.CustomListItems.CustomListItem
import com.paraxco.basictools.FormTools.ListItems.CustomListItems.Model.ProfileData
import com.paraxco.basictools.R

/**
 * .
 */
class ImageListItem : CustomListItem(R.layout.image_item) {
    override fun initializeView(view: View?) {

    }

    override fun requestFocus(): EditText? {
        return null
    }

    override fun resetData(profileData: ProfileData?) {
    }
}