package com.winas_lesson.android.day1.homework

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractActivity : AppCompatActivity() {
    val isTop: Boolean
        get() {
            return equals(App.topActivity)
        }

    protected var isViewLoaded: Boolean = false
    protected var lastPauseTime: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        isViewLoaded = true
    }

    override fun onResume() {
        super.onResume()
        App.topActivity = this
    }

    override fun onPause() {
        lastPauseTime = (System.currentTimeMillis() / 1000L).toInt()
        if (isTop) App.topActivity = null
        super.onPause()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.repeatCount == 0) {
            onBackPressed()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
//        if (isTop && this is Transitionable) {
//            animator.forceDismiss()
//        } else {
        super.onBackPressed()
//        }
    }
}
