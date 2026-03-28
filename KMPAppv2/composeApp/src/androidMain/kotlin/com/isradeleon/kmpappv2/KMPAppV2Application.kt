package com.isradeleon.kmpappv2

import android.app.Application
import com.isradeleon.kmpappv2.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent

class KMPAppV2Application: Application(), KoinComponent {
    /**
     * Set up Koin DI for Android.
     */
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(
                this@KMPAppV2Application
            )
        }
    }
}