package com.winas_lesson.android.day1.sample

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class App : Application() {

    companion object {
        lateinit var context: Context
        var topActivity: AbstractActivity? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
