package com.bootcamp.themoviedb

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import java.lang.Exception

@HiltAndroidApp
class TheMovieDbApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler{ thread, exception ->
            Log.e("Apps Error", "Error System", Exception())
        }
    }
}