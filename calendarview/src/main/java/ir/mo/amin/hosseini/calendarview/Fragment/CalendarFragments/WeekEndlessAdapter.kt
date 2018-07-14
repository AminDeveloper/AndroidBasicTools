//package ir.mo.amin.hosseini.app63167.Fragments.CalendarFragments
//
//import android.support.v4.app.FragmentManager
//import android.support.v4.view.ViewPager
//import ir.mo.amin.hosseini.app63167.Adapters.EndlessFragmentAdapter
//import ir.mo.amin.hosseini.app63167.Helpers.CalendarHelpers.CalendarListHelpers.CalendarListHelperBase
//import ir.hamsaa.persiandatepicker.util.PersianCalendar
//
///**
// * Created by Amin on 8/19/2017.
// */
//class WeekEndlessAdapter(manager: FragmentManager, viewPager: ViewPager) : EndlessFragmentAdapter(manager, viewPager) {
//    fun gotoDate(date: PersianCalendar): Boolean {
//        var weekNumber = CalendarListHelperBase.weekNumberInMonth(date)
//
//        for (i in 0..fragmentListPositive.size - 1) {
//
//            var weekFragment = fragmentListPositive[i] as CalendarWeekFragment
//            if (weekFragment.getData()?.persianYear == date.persianYear &&
//                    weekFragment.getData()?.persianMonth == date.persianMonth &&
//                    weekFragment.getCurentWeek() == weekNumber) {
//                viewPager.currentItem = zeroPoint() + i
//                return true
//
//            }
//
//        }
//        for (i in 0..fragmentListNegetive.size - 1) {
//
//            var weekFragment = fragmentListNegetive[i] as CalendarWeekFragment
//            if (weekFragment.getData()?.persianYear == date.persianYear &&
//                    weekFragment.getData()?.persianMonth == date.persianMonth &&
//                    weekFragment.getCurentWeek() == weekNumber) {
//                viewPager.currentItem = zeroPoint() - i
//                return true
//
//            }
//
//        }
//        return false
//
//    }
//}
