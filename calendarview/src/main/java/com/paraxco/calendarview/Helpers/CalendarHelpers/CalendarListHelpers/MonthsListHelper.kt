package com.paraxco.calendarview.Helpers.CalendarHelpers.CalendarListHelpers

import android.content.Context
import android.view.View
import com.paraxco.calendarview.Model.CalendarModels.CalendarDayItem

/**
 * Created by Amin on 11/2/2017.
 */

class MonthsListHelper(val days: List<CalendarDayItem>, val view: View, val context: Context = view.context) {

    fun doAsignments() {

//        R.id.item_1
        for (index in 1 until days.size) {
            var resID = getResourceId(context, "item_" + index, "id")
//            resID = getResId("item_" + index, R.drawable.)

            val viewFound :View = view.findViewById<View>(resID)
            days[index].initializeView(viewFound)
        }

    }

    fun getResourceId(context: Context, pVariableName: String, pResourcename: String): Int {
        try {
            return context.resources.getIdentifier(pVariableName, pResourcename, context.packageName)
        } catch (e: Exception) {
            e.printStackTrace()
            return -1
        }
        //getResourceId("myIcon", "drawable", getPackageName());


    }

    fun getResId(resName: String, c: Class<*>): Int {

        try {
            val idField = c.getDeclaredField(resName)
            return idField.getInt(idField)
        } catch (e: Exception) {
            e.printStackTrace()
            return -1
        }
//        getResId("icon", Drawable.class);


    }
}
