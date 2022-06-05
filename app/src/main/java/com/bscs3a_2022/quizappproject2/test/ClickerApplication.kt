package com.bscs3a_2022.quizappproject2.test

import android.app.Application
import timber.log.Timber

class ClickerApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}