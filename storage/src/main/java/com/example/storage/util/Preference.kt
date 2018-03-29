package com.example.storage.util

import android.content.Context
import android.content.SharedPreferences
import kotlin.reflect.KProperty

/**
 * Created by ouyangshen on 2017/9/24.
 */
class Preference<T>(val context: Context, val name: String, val default: T) : ReadWriteProperty<Any?, T> {

    //通过属性代理初始化共享参数对象
    val prefs: SharedPreferences by lazy { context.getSharedPreferences("default", Context.MODE_PRIVATE) }

    //接管属性值的获取行为
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name, default)
    }

    //接管属性值的修改行为
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    //利用with函数定义临时的命名空间
    private fun <T> findPreference(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
        return res as T
    }

    private fun <T> putPreference(name: String, value: T) = with(prefs.edit()) {
        //putInt、putString等方法返回Editor对象
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }.apply() //commit方法和apply方法都表示提交修改
    }
}