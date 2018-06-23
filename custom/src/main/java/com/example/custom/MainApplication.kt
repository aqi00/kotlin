package com.example.custom

import android.app.Application
import android.os.Build
import com.example.custom.util.NotifyUtil

/**
 * Created by ouyangshen on 2017/9/17.
 */
class MainApplication : Application() {
    var groupMap = mutableMapOf<String, Boolean>()

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Android 8.0开始必须给每个通知分配对应的渠道
            NotifyUtil.createNotifyChannel(this, getString(R.string.app_name))
        }
    }

    companion object {
        private var instance: MainApplication? = null
        fun instance() = instance!!
    }

}
