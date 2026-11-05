package com.hasancbngl.memecreatorapp

import android.app.Application
import com.hasancbngl.memecreatorapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class MemeCreatorApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MemeCreatorApplication)
        }
    }
}