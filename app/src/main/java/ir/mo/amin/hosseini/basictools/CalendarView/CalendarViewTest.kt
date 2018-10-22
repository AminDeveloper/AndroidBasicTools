package ir.mo.amin.hosseini.basictools.CalendarView

import android.Manifest
import android.os.Bundle
import android.widget.ViewSwitcher
import com.activeandroid.ActiveAndroid
import com.activeandroid.Configuration
import ir.mo.amin.hosseini.basictools.R
import ir.mo.amin.hosseini.calendarview.Fragments.CalendarFragment
import ir.mo.amin.hosseini.calendarview.Model.CalendarModels.ReminderData
import ir.mo.amin.hosseini.commontools.Activities.BaseActivity
import ir.mo.amin.hosseini.commontools.Utils.Permision.PermisionUtils
import ir.mo.amin.hosseini.commontools.Utils.SmartLogger
import ir.mo.amin.hosseini.listtools.ListTools.ViewGroupSwitcher.ViewGroupSwitcher
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