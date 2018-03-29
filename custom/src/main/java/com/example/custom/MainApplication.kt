package com.example.custom

import android.app.Application

/**
 * Created by ouyangshen on 2017/9/17.
 */
class MainApplication : Application() {
    var groupMap = mutableMapOf<String, Boolean>()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null
        fun instance() = instance!!
    }

}
