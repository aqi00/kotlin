package com.example.custom

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_notify_counter.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/9/17.
 */
class NotifyCounterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_counter)
        btn_send_counter.setOnClickListener {
            val title = et_title.text.toString()
            val message = et_message.text.toString()
            sendCounterNotify(title, message)
            toast("计数消息已推送到通知栏。滑掉该消息回到首页")
        }
    }

    private fun sendCounterNotify(title: String, message: String) {
        //声明一个删除通知消息时触发的动作意图
        val cancelIntent = intentFor<MainActivity>()
        val piDelete = PendingIntent.getActivity(this,
                R.string.app_name, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        //开始构建计时消息的各个参数
        val builder = Notification.Builder(this)
        val notify = builder.setDeleteIntent(piDelete)
                .setAutoCancel(true)
                .setUsesChronometer(true)
                .setProgress(100, 60, false)
                .setNumber(99)
                .setSmallIcon(R.drawable.ic_app)
                .setTicker("计数消息来啦")
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_app))
                .setContentTitle(title)
                .setContentText(message).build()
        //获取系统的通知管理器
        val notifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifyMgr.notify(R.string.app_name, notify)
    }

}
