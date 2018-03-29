package com.example.custom.service

import com.example.custom.bean.FreshInfo
import com.example.custom.FreshDetailActivity
import com.example.custom.R
import com.example.custom.util.vibrator

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.widget.RemoteViews

import org.jetbrains.anko.intentFor

class GroupService : Service() {
    lateinit var freshInfo: FreshInfo
    private var isFinished = false

    override fun onStartCommand(intent: Intent, flags: Int, startid: Int): Int {
        freshInfo = intent.getParcelableExtra("fresh")
        handler.post(refreshTask)
        return Service.START_STICKY
    }

    private val TOTAL = 1000
    private var mProgress = 0
    private val handler = Handler()
    private val refreshTask = object : Runnable {
        override fun run() {
            if (mProgress < 100) {
                freshInfo.peopleCount++
                mProgress = freshInfo.peopleCount * 100 / TOTAL
            } else if (!isFinished) {
                isFinished = true
                vibrator.vibrate(3000)
            }
            refreshNotify()
            handler.postDelayed(this, 100)
        }
    }

    private var notify: Notification? = null
    private fun refreshNotify() {
        val desc = "当前已有${freshInfo.peopleCount}人加入团购"
        val notify_group = RemoteViews(this.packageName, R.layout.notify_group)
        notify_group.setImageViewResource(R.id.iv_icon, freshInfo.imageId)
        notify_group.setTextViewText(R.id.tv_title, freshInfo.name)
        notify_group.setTextViewText(R.id.tv_group, desc)
        notify_group.setProgressBar(R.id.pb_group, 100, mProgress, false)

        val intent = intentFor<FreshDetailActivity>("fresh" to freshInfo)
        val contentIntent = PendingIntent.getActivity(this,
                R.string.app_name, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = Notification.Builder(this)
        notify = builder.setContentIntent(contentIntent)
                .setContent(notify_group)
                .setTicker("生鲜团购运行中")
                .setSmallIcon(R.drawable.ic_app).build()
        startForeground(9, notify)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (notify != null) {
            stopForeground(true)
        }
    }

    override fun onBind(intent: Intent): IBinder? = null

}
