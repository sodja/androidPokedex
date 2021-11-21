package com.codesodja.androidpokedex

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyPokedexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("application", "launch")
    }
}