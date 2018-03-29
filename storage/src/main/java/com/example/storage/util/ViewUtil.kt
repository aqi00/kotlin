package com.example.storage.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Created by ouyangshen on 2016/9/24.
 */
object ViewUtil {

    fun getMaxLength(et: EditText): Int {
        var length = 0
        try {
            val inputFilters = et.filters
            for (filter in inputFilters) {
                val c = filter.javaClass
                if (c.name == "android.text.InputFilter\$LengthFilter") {
                    val f = c.declaredFields
                    for (field in f) {
                        if (field.name == "mMax") {
                            field.isAccessible = true
                            length = field.get(filter) as Int
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return length
    }

    fun hideAllInputMethod(act: Activity) {
        val imm = act.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //软键盘如果已经打开则关闭之
        if (imm.isActive == true) {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    fun hideOneInputMethod(act: Activity, v: View) {
        val imm = act.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }

}
