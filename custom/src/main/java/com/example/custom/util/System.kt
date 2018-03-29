package com.example.custom.util

import android.app.NotificationManager
import android.content.Context
import android.os.Vibrator

/**
 * Created by ouyangshen on 2017/10/8.
 */
//获取震动器
//fun Context.getVibrator() : Vibrator {
//    return getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//}
//获取震动器
//利用扩展函数实现扩展属性，在Activity代码中即可直接使用vibrator
val Context.vibrator : Vibrator
    get() = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//获取通知管理器
//试试在Activity代码中调用“notifier.notify(R.string.app_name, notify)”
val Context.notifier: NotificationManager
    get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager