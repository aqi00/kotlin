package com.example.grammar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.grammar.util.*

import kotlinx.android.synthetic.main.activity_system.*
import java.util.*

class SystemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system)

        //val array:Array<Int> = arrayOf(1, 2, 3, 4, 5)
        val array:Array<Double> = arrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
        btn_function_extend.setOnClickListener {
            //把下标为0和3的两个数组元素进行交换
            //array可以是整型数组，也可以是双精度数组
            array.swap(0, 3)
            setArrayStr<Double>(array)
        }

        var count = 0
        var string_array:Array<String> = arrayOf("How", "do", "you", "do", "I'm   ", "Fine")
        btn_extend_higher.setOnClickListener {
            tv_function_result.text = when (count++%3) {
            //下面是结合高阶函数与扩展函数的调用代码
                0 -> "字符串数组按长度比较的最大值为${string_array.maxCustomize({ a, b -> a.length > b.length })}"
                1 -> "字符串数组的默认最大值(使用高阶函数)为${string_array.maxCustomize({ a, b -> a > b })}"
                else -> "字符串数组按去掉空格再比较长度的最大值为${string_array.maxCustomize({ a, b -> a.trim().length > b.trim().length })}"
            }
        }

        btn_extend_date.setOnClickListener {
            //以下方法调用自ExtendDate.kt，采取了扩展函数的方式
            tv_function_result.text = "扩展函数：" + when (count++%5) {
                0 -> "当前日期时间为${Date().getNowDateTime()}"
                1 -> "当前日期为${Date().getNowDate()}"
                2 -> "当前时间为${Date().getNowTime()}"
                3 -> "当前毫秒时间为${Date().getNowTimeDetail()}"
                else -> "当前中文日期时间为${Date().getFormatTime("yyyy年MM月dd日HH时mm分ss秒")}"
            }
        }

        btn_object_date.setOnClickListener {
            //以下方法调用自DateUtil.kt，采取了单例对象的方式
            tv_function_result.text = "单例对象：" + when (count++%5) {
                0 -> "当前日期时间为${DateUtil.nowDateTime}"
                1 -> "当前日期为${DateUtil.nowDate}"
                2 -> "当前时间为${DateUtil.nowTime}"
                3 -> "当前毫秒时间为${DateUtil.nowTimeDetail}"
                else -> "当前中文日期时间为${DateUtil.getFormatTime("yyyy年MM月dd日HH时mm分ss秒")}"
            }
        }

    }

    //只有内联函数才可以被具体化
    inline fun <reified T : Number> setArrayStr(array:Array<T>) {
        var str:String = "数组元素依次排列："
        for (item in array) {
            str = str + item.toString() + ", "
        }
        tv_function_result.text = str
    }

}
