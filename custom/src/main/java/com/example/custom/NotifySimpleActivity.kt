package com.example.custom

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_notify_simple.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/9/17.
 */
class NotifySimpleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_simple)
        btn_send_simple.setOnClickListener {
            val title = et_title.text.toString()
            val message = et_message.text.toString()
            sendSimpleNotify(title, message)
            toast("简单消息已推送到通知栏。点击该消息回到首页")
        }
    }

    private fun sendSimpleNotify(title: String, message: String) {
        //声明一个点击通知栏时触发的动作意图
        val clickIntent = intentFor<MainActivity>()
        val piClick = PendingIntent.getActivity(this,
                R.string.app_name, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        //开始构建简单消息的各个参数
        var builder = Notification.Builder(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Android 8.0开始必须给每个通知分配对应的渠道
            builder = Notification.Builder(this, getString(R.string.app_name))
        }
        val notify = builder.setContentIntent(piClick)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_app)
                //.setSubText("这里是副本")
                .setTicker("简单消息来啦")
                .setWhen(System.currentTimeMillis())
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_app))
                .setContentTitle(title)
                .setContentText(message).build()
        //获取系统的通知管理器
        val notifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifyMgr.notify(R.string.app_name, notify)
    }

}
