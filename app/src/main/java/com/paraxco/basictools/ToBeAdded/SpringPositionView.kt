//package com.paraxco.khayerin.core.custom_view
//
//import android.content.Context
//import android.support.animation.SpringAnimation
//import android.support.animation.SpringForce
//import android.util.AttributeSet
//import android.view.MotionEvent
//import android.view.View
//// in gradle:
////implementation "com.android.support:support-dynamic-animation:27.1.1"
//
//class SpringPositionView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
//
//    private companion object Params {
//        private const val STIFFNESS = SpringForce.STIFFNESS_MEDIUM
//        private const val DAMPING_RATIO = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
//    }
//
//    private lateinit var xAnim: SpringAnimation
//    // private lateinit var yAnim: SpringAnimation
//
//    init {
//
//        viewTreeObserver.addOnGlobalLayoutListener {
//            xAnim = SpringAnimation(this, SpringAnimation.X).apply {
//                spring = SpringForce(x).apply {
//                    stiffness = STIFFNESS
//                    dampingRatio = DAMPING_RATIO
//                }
//            }
//            /*    yAnim = SpringAnimation(this, SpringAnimation.Y).apply {
//                    spring = SpringForce(y).apply {
//                        stiffness = STIFFNESS
//                        dampingRatio = DAMPING_RATIO
//                    }
//                }*/
//        }
//
//        var dX = 0f
//        //   var dY = 0f
//        setOnTouchListener { view, event ->
//            when (event.actionMasked) {
//                MotionEvent.ACTION_DOWN -> {
//                    dX = view.x - event.rawX
//                    //    dY = view.y - event.rawY
//
//                    xAnim.cancel()
//                    //      yAnim.cancel()
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    animate()
//                            .x(event.rawX + dX)
//                            //  .y(event.rawY + dY)
//                            .setDuration(0)
//                            .start()
//                }
//                MotionEvent.ACTION_UP -> {
//                    xAnim.start()
//                    //        yAnim.start()
//                }
//            }
//            true
//        }
//    }
//
//}
//
