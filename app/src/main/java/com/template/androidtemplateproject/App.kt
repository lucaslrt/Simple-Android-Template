package com.template.androidtemplateproject

import android.annotation.SuppressLint
import android.app.Application
import com.template.androidtemplateproject.di.homeModule
import com.template.androidtemplateproject.di.networkModule
import com.template.androidtemplateproject.di.weatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@SuppressLint("Registered")
class App : Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            //androidLogger()
            androidContext(this@App)
            modules(listOf(networkModule, homeModule, weatherModule))
        }
    }
}