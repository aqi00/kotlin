package com.example.simple.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by ouyangshen on 2017/9/7.
 */
//@Parcelize注解表示自动实现Parcelable接口的相关方法
@Parcelize
data class MessageInfo(val content: String, val send_time: String) : Parcelable {
}
