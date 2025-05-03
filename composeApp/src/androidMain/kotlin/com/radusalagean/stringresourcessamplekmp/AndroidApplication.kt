package com.radusalagean.stringresourcessamplekmp

import android.app.Application

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        application(AndroidPlatform())
    }
}