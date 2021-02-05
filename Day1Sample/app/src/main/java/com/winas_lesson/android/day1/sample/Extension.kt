package com.winas_lesson.android.day1.sample

import android.view.View
import android.widget.FrameLayout
import java.text.SimpleDateFormat
import java.util.*

// Date

val Date.milliseconds: Double
    get() {
        val milliseconds = time / 1000L
        // describe milliseconds in format to string
        return "${milliseconds.toInt()}${SimpleDateFormat(".SSS").format(this)}".toDouble()
    }

// List
val <T> List<T>.randomValue: T
    get() {
        val rand = java.util.Random().nextInt(size - 1)
        return get(rand)
    }

// View
var View.layoutWidth: Int
    get() {
        val params = this.layoutParams
        return params.width
    }
    set(value) {
        var params = this.layoutParams
        params.width = value
        this.layoutParams = params
    }
var View.layoutHeight: Int
    get() {
        val params = this.layoutParams
        return params.height
    }
    set(value) {
        var params = this.layoutParams
        params.height = value
        this.layoutParams = params
    }
var View.layoutGravity: Int?
    get() {
        return (layoutParams as? FrameLayout.LayoutParams)?.gravity
    }
    set(value) {
        (layoutParams as? FrameLayout.LayoutParams)?.let { params ->
            if (value != null) {
                params.gravity = value
                layoutParams = params
            }
        }
    }
