package com.example.custom.service

import com.example.custom.MainActivity
import com.example.custom.R

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.*
import android.util.Log
import android.widget.RemoteViews
import org.jetbrains.anko.intentFor

/**
 * Created by ouyangshen on 2016/10/14.
 */
class MusicService : Service() {
    inner class LocalBinder : Binder() {
        val service: MusicService
            get() = this@MusicService
    }

    private val mBinder = LocalBinder()
    override fun onBind(intent: Intent): IBinder? = mBinder

    private var mSong: String = ""
    private var PAUSE_EVENT = ""
    private var isPlay = true
    private var mBaseTime: Long = 0
    private var mPauseTime: Long = 0
    private var mProgress = 0
    private val handler = Handler()
    private val playTask = object : Runnable {
        override fun run() {
            if (isPlay) {
                if (mProgress < 100) {
                    mProgress += 2
                } else {
                    mProgress = 0
                }
                handler.postDelayed(this, 1000)
            }
            val notify = getNotify(this@MusicService, PAUSE_EVENT, mSong, isPlay, mProgress, mBaseTime)
            //持续刷新通知栏上的播放进度
            startForeground(2, notify)
        }
    }

    private fun getNotify(ctx: Context, event: String, song: String, isPlay: Boolean, progress: Int, time: Long): Notification {
        val pIntent = Intent(event)
        val nIntent = PendingIntent.getBroadcast(ctx,
                R.string.app_name, pIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notify_music = RemoteViews(ctx.packageName, R.layout.notify_music)
        if (isPlay) {
            notify_music.setTextViewText(R.id.btn_play, "暂停")
            notify_music.setTextViewText(R.id.tv_play, "${song}正在播放")
            notify_music.setChronometer(R.id.chr_play, time, "%s", true)
        } else {
            notify_music.setTextViewText(R.id.btn_play, "继续")
            notify_music.setTextViewText(R.id.tv_play, "${song}暂停播放")
            notify_music.setChronometer(R.id.chr_play, time, "%s", false)
        }
        notify_music.setProgressBar(R.id.pb_play, 100, progress, false)
        notify_music.setOnClickPendingIntent(R.id.btn_play, nIntent)
        val intent = ctx.intentFor<MainActivity>()
        val cIntent = PendingIntent.getActivity(ctx,
                R.string.app_name, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        var builder = Notification.Builder(ctx)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Android 8.0开始必须给每个通知分配对应的渠道
            builder = Notification.Builder(this, getString(R.string.app_name))
        }
        return builder.setContentIntent(cIntent)
                .setContent(notify_music)
                .setTicker(song)
                .setSmallIcon(R.drawable.tt_s).build()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startid: Int): Int {
        mBaseTime = SystemClock.elapsedRealtime()
        isPlay = intent.getBooleanExtra("is_play", true)
        mSong = intent.getStringExtra("song")
        handler.postDelayed(playTask, 200)
        return Service.START_STICKY
    }

    override fun onCreate() {
        PAUSE_EVENT = resources.getString(R.string.pause_event)
        pauseReceiver = PauseReceiver()
        registerReceiver(pauseReceiver, IntentFilter(PAUSE_EVENT))
        super.onCreate()
    }

    override fun onDestroy() {
        unregisterReceiver(pauseReceiver)
        super.onDestroy()
    }

    private var pauseReceiver: PauseReceiver? = null
    //定义一个处理播放/暂停事件的广播接收器内部类
    inner class PauseReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if (intent != null) {
                isPlay = !isPlay
                if (isPlay) {
                    handler.postDelayed(playTask, 200)
                    if (mPauseTime > 0) {
                        val gap = SystemClock.elapsedRealtime() - mPauseTime
                        mBaseTime += gap
                    }
                } else {
                    mPauseTime = SystemClock.elapsedRealtime()
                }
            }
        }
    }
}
