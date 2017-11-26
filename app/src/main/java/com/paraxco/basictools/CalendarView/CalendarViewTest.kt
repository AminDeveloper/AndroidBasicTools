package com.paraxco.basictools.CalendarView

import android.Manifest
import android.os.Bundle
import android.widget.ViewSwitcher
import com.activeandroid.ActiveAndroid
import com.activeandroid.Configuration
import com.paraxco.basictools.R
import com.paraxco.calendarview.Fragments.CalendarFragment
import com.paraxco.calendarview.Model.CalendarModels.ReminderData
import com.paraxco.commontools.Activities.BaseActivity
import com.paraxco.commontools.Utils.Permision.PermisionUtils
import com.paraxco.commontools.Utils.SmartLogger
import com.paraxco.listtools.ListTools.ViewGroupSwitcher.ViewGroupSwitcher
import kotlinx.android.synthetic.main.calendar_view_test.*


/**
 *
 */
class CalendarViewTest : BaseActivity() {
    companion object {
        var calendarViewTest: CalendarViewTest? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_view_test)
        initializeORM()
        calendarViewTest = this
        var swicher=ViewGroupSwitcher(insertion_point)
        swicher.addView(CalendarFragment())
        swicher.showView(0)

    }

    private val DATA_BASE_NAME = "DataBaseName"

    private fun initializeORM() {
        val dbConfiguration = Configuration.Builder(this)
                .setDatabaseName(DATA_BASE_NAME)
                //                .setDatabaseVersion(3)
                .setModelClasses(ReminderData::class.java
                ).create()
        ActiveAndroid.initialize(dbConfiguration)
    }

}