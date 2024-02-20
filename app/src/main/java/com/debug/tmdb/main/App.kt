package com.debug.tmdb.main

import android.app.Application
import com.debug.tmdb.main.data.di.DataModule
import com.debug.tmdb.main.presenter.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        PresentationModule.load()
        DataModule.load()
    }
}