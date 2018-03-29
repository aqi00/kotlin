package com.example.grammar.special

/**
 * Created by ouyangshen on 2017/7/31.
 */
class LogUtil<T> {

    fun <T> getString(tag:String, vararg otherInfo: T?):String {
        var str:String = "$tag："
        for (item in otherInfo) {
            str = "$str${item.toString()}，"
        }
        return str
    }

}