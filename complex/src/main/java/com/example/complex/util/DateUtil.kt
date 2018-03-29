package com.example.complex.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Created by ouyangshen on 2016/9/24.
 */
@SuppressLint("SimpleDateFormat")
object DateUtil {

    val nowDateTime: String
        get() {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return sdf.format(Date())
        }

    val nowDate: String
        get() {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            return sdf.format(Date())
        }

    val nowTime: String
        get() {
            val sdf = SimpleDateFormat("HH:mm:ss")
            return sdf.format(Date())
        }

    val nowTimeDetail: String
        get() {
            val sdf = SimpleDateFormat("HH:mm:ss.SSS")
            return sdf.format(Date())
        }

    fun getFormatTime(format: String=""): String {
        val ft: String = format
        val sdf = if (!ft.isEmpty()) SimpleDateFormat("format")
                    else SimpleDateFormat("yyyyMMddHHmmss")
        return sdf.format(Date())
    }

}
