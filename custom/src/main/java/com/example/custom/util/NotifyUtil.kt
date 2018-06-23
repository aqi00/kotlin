package com.example.custom.util

import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color


/**
 * Created by ouyangshen on 2018/6/20.
 */
object NotifyUtil {

    @TargetApi(Build.VERSION_CODES.O)
    // 创建通知渠道。Android 8.0开始必须给每个通知分配对应的渠道
    fun createNotifyChannel(ctx: Context, channelId: String) {
        // 创建一个默认重要性的通知渠道
        val channel = NotificationChannel(channelId,
                "Channel", NotificationManager.IMPORTANCE_DEFAULT)
        // 设置推送通知之时的铃声。null表示静音推送
        channel.setSound(null, null)
        // 设置在桌面图标右上角展示小红点
        channel.enableLights(true)
        // 设置小红点的颜色
        channel.lightColor = Color.RED
        // 在长按桌面图标时显示该渠道的通知
        channel.setShowBadge(true)
        // 从系统服务中获取通知管理器
        val notifyMgr = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // 创建指定的通知渠道
        notifyMgr.createNotificationChannel(channel)
    }

}