package com.example.grammar.util

import java.text.SimpleDateFormat
import java.util.Date

/**
 * Created by ouyangshen on 2017/9/23.
 */
//方法名称前面的Date.表示该方法扩展自Date类
//返回的日期时间格式形如2017-10-01 10:00:00
fun Date.getNowDateTime(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return sdf.format(this)
}

//只返回日期字符串
fun Date.getNowDate(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    return sdf.format(this)
}

//只返回时间字符串
fun Date.getNowTime(): String {
    val sdf = SimpleDateFormat("HH:mm:ss")
    return sdf.format(this)
}

//返回详细的时间字符串，精确到毫秒
fun Date.getNowTimeDetail(): String {
    val sdf = SimpleDateFormat("HH:mm:ss.SSS")
    return sdf.format(this)
}

//返回开发者指定格式的日期时间字符串
fun Date.getFormatTime(format: String=""): String {
    var ft = format
    val sdf = if (!ft.isEmpty()) SimpleDateFormat(ft)
    else SimpleDateFormat("yyyyMMddHHmmss")
    return sdf.format(this)
}
