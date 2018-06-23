package com.example.custom

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.widget.RemoteViews

import kotlinx.android.synthetic.main.activity_notify_custom.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/9/17.
 */
class NotifyCustomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_custom)
        val PAUSE_EVENT = resources.getString(R.string.pause_event)
        btn_send_custom.setOnClickListener {
            val contentView = getNotifyMusic(this, PAUSE_EVENT,
                    et_song.text.toString(), true, 50, SystemClock.elapsedRealtime())
            sendCustomNotify(this, et_song.text.toString(), contentView, null)
            toast("歌曲${et_song.text}已推送到通知栏")
        }
        btn_send_expand.setOnClickListener {
            val contentView = getNotifyMusic(this, PAUSE_EVENT,
                    et_song.text.toString(), true, 50, SystemClock.elapsedRealtime())
            val bigContentView = getNotifyExpand(this, PAUSE_EVENT,
                    et_song.text.toString(), true, 50, SystemClock.elapsedRealtime())
            sendCustomNotify(this, et_song.text.toString(), contentView, bigContentView)
            toast("歌曲${et_song.text}已推送到折叠式通知")
        }
    }

    private fun sendCustomNotify(ctx: Context, song: String, contentView: RemoteViews, bigContentView: RemoteViews?) {
        //声明一个点击通知消息时触发的动作意图
        val intent = ctx.intentFor<MainActivity>()
        val contentIntent = PendingIntent.getActivity(ctx,
                R.string.app_name, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        var builder = Notification.Builder(ctx)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Android 8.0开始必须给每个通知分配对应的渠道
            builder = Notification.Builder(this, getString(R.string.app_name))
        }
        builder.setContentIntent(contentIntent)
                .setContent(contentView) //采用自定义的通知布局
                .setTicker(song)
                .setSmallIcon(R.drawable.tt_s)
        val notify = builder.build()
        //正常高度的自定义通知
        notify.contentView = contentView
        if (bigContentView != null) {
            //展开后的自定义通知
            notify.bigContentView = bigContentView
        }
        //获取系统的通知管理器
        val notifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifyMgr.notify(R.string.app_name, notify)
    }

    //获取播放器的通知栏布局
    private fun getNotifyMusic(ctx: Context, event: String, song: String, isPlay: Boolean, progress: Int, time: Long): RemoteViews {
        //从notify_music.xml布局文件构造远程视图对象
        val notify_music = RemoteViews(ctx.packageName, R.layout.notify_music)
        if (isPlay) {
            notify_music.setTextViewText(R.id.btn_play, "暂停")
            notify_music.setTextViewText(R.id.tv_play, song + "正在播放")
            notify_music.setChronometer(R.id.chr_play, time, "%s", true)
        } else {
            notify_music.setTextViewText(R.id.btn_play, "继续")
            notify_music.setTextViewText(R.id.tv_play, song + "暂停播放")
            notify_music.setChronometer(R.id.chr_play, time, "%s", false)
        }
        notify_music.setProgressBar(R.id.pb_play, 100, progress, false)
        val pIntent = Intent(event)
        val piPause = PendingIntent.getBroadcast(
                ctx, R.string.app_name, pIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        //设置暂停/继续按钮的点击动作对应的广播事件
        notify_music.setOnClickPendingIntent(R.id.btn_play, piPause)
        return notify_music
    }

    //获取折叠视图展开后的通知栏布局
    private fun getNotifyExpand(ctx: Context, event: String, song: String, isPlay: Boolean, progress: Int, time: Long): RemoteViews {
        //从notify_expand.xml布局文件构造远程视图对象
        val notify_expand = RemoteViews(ctx.packageName, R.layout.notify_expand)
        if (isPlay) {
            notify_expand.setTextViewText(R.id.btn_play, "暂停")
            notify_expand.setTextViewText(R.id.tv_play, song + "正在播放")
            notify_expand.setChronometer(R.id.chr_play, time, "%s", true)
        } else {
            notify_expand.setTextViewText(R.id.btn_play, "继续")
            notify_expand.setTextViewText(R.id.tv_play, song + "暂停播放")
            notify_expand.setChronometer(R.id.chr_play, time, "%s", false)
        }
        notify_expand.setProgressBar(R.id.pb_play, 100, progress, false)
        val pIntent = Intent(event)
        val piPause = PendingIntent.getBroadcast(
                ctx, R.string.app_name, pIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        //设置播放按钮的点击动作对应的广播事件
        notify_expand.setOnClickPendingIntent(R.id.btn_play, piPause)
        val bIntent = Intent(ctx, NotifyCustomActivity::class.java)
        val piBack = PendingIntent.getActivity(ctx,
                R.string.app_name, bIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        //设置返回按钮的点击动作对应的跳转事件
        notify_expand.setOnClickPendingIntent(R.id.btn_back, piBack)
        return notify_expand
    }

}
