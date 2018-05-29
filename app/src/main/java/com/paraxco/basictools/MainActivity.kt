package com.paraxco.basictools

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.paraxco.basictools.Activities.ListTools.ListToolsTest
import com.paraxco.basictools.CalendarView.CalendarViewTest
import com.paraxco.basictools.Commontools.Observers.ObserverList
import com.paraxco.basictools.Commontools.Observers.TestObserver
import com.paraxco.commontools.Activities.BaseActivity
import com.paraxco.commontools.Utils.SmartLogger
import kotlinx.android.synthetic.main.main_activity.*


/**
 * Created by Amin on 18/11/2017.
 */

class MainActivity : BaseActivity(), TestObserver.ObserverTest {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SmartLogger.initLogger(applicationContext)
        setContentView(R.layout.main_activity)
        showListToolsTest.setOnClickListener({
            startListToolsTest()
        })
        showNotificationTest.setOnClickListener({
            startNotificationBadgeTest()
        })
        showCalendarViewTest.setOnClickListener({
            startCalendarViewTest()
        })
        showImageToolsTest.setOnClickListener({
            startImageToolsTest()
        })
        ObserverTest.setOnClickListener({
            ObserverList.getTestObserver().informObservers(listOf("abc", "cde"))
        })
        ObserverList.getTestObserver().addObserver(this)


    }

    override fun onDestroy() {
        super.onDestroy()
        ObserverList.getTestObserver().removeObserver(this)

    }

    override fun observeChanges(list: MutableList<String>?) {
        Toast.makeText(this, list!!.size.toString(), Toast.LENGTH_LONG).show()
    }

    private fun startListToolsTest() {
        val myIntent = Intent(this, ListToolsTest::class.java)
//        myIntent.putExtra("key", value) //Optional parameters
        this.startActivity(myIntent)

    }

    private fun startImageToolsTest() {
        val myIntent = Intent(this, ImageToolsTest::class.java)
//        myIntent.putExtra("key", value) //Optional parameters
        this.startActivity(myIntent)
    }

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