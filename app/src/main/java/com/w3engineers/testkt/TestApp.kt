package com.w3engineers.testkt

import android.app.Application
import android.content.Context

class TestApp: Application() {

    companion object{
        private lateinit var context: Context
        fun onContext():Context{
            return context
        }

    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext;
    }
}