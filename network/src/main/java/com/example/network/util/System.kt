package com.example.network.util

import android.app.AlarmManager
import android.app.DownloadManager
import android.app.NotificationManager
import android.content.Context
import android.location.Criteria
import android.location.LocationManager
import android.media.AudioManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Vibrator
import android.telephony.TelephonyManager

/**
 * Created by ouyangshen on 2017/10/8.
 */
//获取震动器
val Context.vibrator: Vibrator
    get() = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//获取通知管理器
val Context.notifier: NotificationManager
    get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//获取下载管理器
val Context.downloader: DownloadManager
    get() = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//获取定位管理器
val Context.locator: LocationManager
    get() = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//获取连接管理器
val Context.connector: ConnectivityManager
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//获取电话管理器
val Context.telephone: TelephonyManager
    get() = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//获取无线管理器
val Context.wifi: WifiManager
    get() = getSystemService(Context.WIFI_SERVICE) as WifiManager
//获取闹钟管理器
val Context.alarm: AlarmManager
    get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//获取音频管理器
val Context.audio: AudioManager
    get() = getSystemService(Context.AUDIO_SERVICE) as AudioManager
//获取定位规则
val Context.criteria: Criteria
    get() {
        var cri = Criteria()
        cri.accuracy = Criteria.ACCURACY_FINE
        cri.isAltitudeRequired = true
        cri.isBearingRequired = true
        cri.isCostAllowed = true
        cri.powerRequirement = Criteria.POWER_LOW
        return cri
    }
