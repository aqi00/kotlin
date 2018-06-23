package com.example.custom

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.activity_notify_large.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast

class NotifyLargeActivity : AppCompatActivity() {
    private val styles = listOf("大文字风格", "大图片风格", "收件箱风格")
    private var type = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_large)
        sp_style.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = styles[0]
        tv_spinner.setOnClickListener {
            selector("请选择大视图风格", styles) { i ->
                tv_spinner.text = styles[i]
                type = i
            }
        }

        btn_send_large.setOnClickListener {
            getStyleAndSend(et_title.text.toString(), et_message.text.toString(), type)
        }
    }
    
    private fun getStyleAndSend(title: String, message: String, type: Int) {
        var style: NotificationCompat.Style? = null
        when (type) {
            0 -> { //声明大文本风格
                style = NotificationCompat.BigTextStyle()
                style.setBigContentTitle(title)
                style.setSummaryText(message)
                style.bigText("这是一条大文字风格的通知消息")
            }
            1 -> { //声明大图片风格
                style = NotificationCompat.BigPictureStyle()
                style.setBigContentTitle(title)
                style.setSummaryText(message)
                style.bigLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.icon_financer))
                style.bigPicture(BitmapFactory.decodeResource(resources, R.drawable.icon_sunshine))
            }
            2 -> { //声明收件箱风格
                style = NotificationCompat.InboxStyle()
                style.setBigContentTitle(title)
                style.setSummaryText(message)
                style.addLine("天青色等烟雨，而我在等你")
                style.addLine("炊烟袅袅升起，隔江千万里")
                style.addLine("在瓶底书汉隶仿前朝的飘逸")
            }
        }
        sendLargeNotify(title, message, style)
        toast("大视图消息已推送到通知栏。")
    }

    private fun sendLargeNotify(title: String, message: String, style: NotificationCompat.Style?) {
        //声明一个“取消”按钮的动作意图
        val cancelIntent = intentFor<NotifyLargeActivity>()
        val piCancel = PendingIntent.getActivity(this,
                R.string.app_name, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        //声明一个“前往”按钮的动作意图
        val confirmIntent = intentFor<NotifyLargeActivity>()
        val piConfirm = PendingIntent.getActivity(this,
                R.string.app_name, confirmIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        //大视图通知需要通过NotificationCompat.Builder来构建
        var builder = NotificationCompat.Builder(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Android 8.0开始必须给每个通知分配对应的渠道
            builder = NotificationCompat.Builder(this, getString(R.string.app_name))
        }
        builder.setSmallIcon(R.drawable.ic_app)
                .setTicker("大视图消息来啦")
                .setWhen(System.currentTimeMillis())
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_app))
                .setContentTitle(title)
                .setContentText(message)
                .setDefaults(Notification.DEFAULT_ALL) //设置大视图通知的提醒方式
                .setStyle(style) //设置大视图通知的风格类型
                .addAction(R.drawable.icon_cancel, "取消", piCancel) //添加取消按钮
                .addAction(R.drawable.icon_confirm, "前往", piConfirm) //添加前往按钮
        val notify = builder.build()
        //获取系统的通知管理器
        val notifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifyMgr.notify(1, notify)
    }

}
