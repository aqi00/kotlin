package com.example.network.util

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.CharacterStyle

/**
 * Created by ouyangshen on 2017/10/7.
 */
//字符串中的关键语句用指定样式高亮显示
fun String.highlight(key: String, style: CharacterStyle): SpannableString {
    val spanText = SpannableString(this)
    val beginPos = this.indexOf(key)
    val endPos = beginPos + key.length
    spanText.setSpan(style, beginPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spanText
}