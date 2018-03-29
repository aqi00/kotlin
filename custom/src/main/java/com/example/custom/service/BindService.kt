package com.example.custom.service

import com.example.custom.ServiceBindActivity

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

/**
 * Created by ouyangshen on 2016/10/14.
 */
class BindService : Service() {
    private val mBinder = LocalBinder()

    inner class LocalBinder : Binder() {
        val service: BindService
            get() = this@BindService
    }

    override fun onCreate() {
        ServiceBindActivity.showText("创建服务")
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder? {
        val bundle = intent.extras
        val request_content = bundle.getString("request_content")
        ServiceBindActivity.showText("绑定服务，收到请求内容：${request_content}")
        return mBinder
    }

    override fun onUnbind(intent: Intent): Boolean {
        ServiceBindActivity.showText("解绑服务")
        return true
    }

}
