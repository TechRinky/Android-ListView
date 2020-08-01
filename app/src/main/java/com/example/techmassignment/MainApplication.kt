package com.example.techmassignment

import android.app.Application
import android.content.Context

/**
 * This is the main application class
 */
class MainApplication : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}