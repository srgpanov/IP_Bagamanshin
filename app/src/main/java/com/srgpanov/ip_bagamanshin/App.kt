package com.srgpanov.ip_bagamanshin

import android.app.Application
import com.srgpanov.ip_bagamanshin.di.AppComponent
import com.srgpanov.ip_bagamanshin.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent
        private set

    companion object {
        lateinit var instance: App
            private set

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent= DaggerAppComponent.builder().build()

    }
}