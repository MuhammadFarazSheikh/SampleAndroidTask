package com.composeapp.sampleandroidtask

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppClass:Application()
{
    companion object
    {
        lateinit var instance: Context
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        instance = this
    }
}