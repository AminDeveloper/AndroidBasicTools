package com.paraxco.basictools

import android.content.Intent
import android.os.Bundle
import com.paraxco.basictools.CalendarView.CalendarViewTest
import com.paraxco.commontools.Activities.BaseActivity
import kotlinx.android.synthetic.main.main_activity.*


/**
 * Created by Amin on 18/11/2017.
 */

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        showNotificationTest.setOnClickListener({
            startNotificationBadgeTest()
        })
        showCalendarViewTest.setOnClickListener({
            startCalendarViewTest()
        })
        showImageToolsTest.setOnClickListener({
            startImageToolsTest()
        })

    }

    private fun startImageToolsTest() {
        val myIntent = Intent(this, ImageToolsTest::class.java)
//        myIntent.putExtra("key", value) //Optional parameters
        this.startActivity(myIntent)    }

    private fun startNotificationBadgeTest() {

        val myIntent = Intent(this, NotificationBadgeTest::class.java)
//        myIntent.putExtra("key", value) //Optional parameters
        this.startActivity(myIntent)
    }
    private fun startCalendarViewTest() {

        val myIntent = Intent(this, CalendarViewTest::class.java)
//        myIntent.putExtra("key", value) //Optional parameters
        this.startActivity(myIntent)
    }

//    private fun startPage(NextActivity: Class<*>) {
//        val myIntent = Intent(this, NextActivity::class.java)
////        myIntent.putExtra("key", value) //Optional parameters
//        this.startActivity(myIntent)
//    }
}