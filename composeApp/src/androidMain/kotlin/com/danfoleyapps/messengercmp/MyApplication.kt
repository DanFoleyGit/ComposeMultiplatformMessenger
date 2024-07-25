package com.danfoleyapps.messengercmp

import android.app.Application
import di.initKoin
import org.koin.android.ext.koin.androidContext

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // here we can inject the context the dependency that android needs
        initKoin{
            androidContext(this@MyApplication)
        }
    }
}