package com.example.grammar.forest

/**
 * Created by ouyangshen on 2017/8/20.
 */
//在类名后面添加“<T>”，表示这是一个模板类
class River<T> (var name:String, var length:T) {
    fun getInfo():String {
        var unit:String = when (length) {
            is String -> "米"
            //Int、Long、Float、Double都是数字类型Number
            is Number -> "m"
            else -> ""
        }
        return "${name}的长度是$length$unit。"
    }
}