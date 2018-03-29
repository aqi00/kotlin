package com.example.grammar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_equal.*
import java.util.Date

class EqualActivity : AppCompatActivity() {
    var isEqual:Boolean = true
    var count:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equal)

        val helloHe:String = "你好"
        val helloShe:String = "妳好"
        btn_equal_struct.setOnClickListener {
            if (isEqual) {
                tv_check_title.text = "比较 $helloHe 和 $helloShe 是否相等"
                //比较两个字符串是否相等的Java写法是 helloHe.equals(helloShe)
                val result = helloHe == helloShe
                tv_check_result.text = "==的比较结果是$result"
            } else {
                tv_check_title.text = "比较 $helloHe 和 $helloShe 是否不等"
                //比较两个字符串是否不等的Java写法是 !helloHe.equals(helloShe)
                val result = helloHe != helloShe
                tv_check_result.text = "!=的比较结果是$result"
            }
            isEqual = !isEqual
        }

        val date1:Date = Date()
        val date2:Any = date1.clone() //从date1原样克隆一份到date2
        btn_equal_refer.setOnClickListener {
            when (count++%4) {
                0 -> {
                    tv_check_title.text = "比较 date1 和 date2 是否结构相等"
                    //结构相等比较的是二者的值
                    val result = date1 == date2
                    tv_check_result.text = "==的比较结果是$result"
                }
                1 -> {
                    tv_check_title.text = "比较 date1 和 date2 是否结构不等"
                    //结构不等比较的是二者的值
                    val result = date1 != date2
                    tv_check_result.text = "!=的比较结果是$result"
                }
                2 -> {
                    tv_check_title.text = "比较 date1 和 date2 是否引用相等"
                    //引用相等比较的是二者是不是同一个东西，即使克隆的一模一样也不是一个东西
                    val result = date1 === date2
                    tv_check_result.text = "===的比较结果是$result"
                }
                else -> {
                    tv_check_title.text = "比较 date1 和 date2 是否引用不等"
                    //引用相等倒过来便是引用不等
                    val result = date1 !== date2
                    tv_check_result.text = "!==的比较结果是$result"
                }
            }
        }

        val oneLong:Long = 1L
        btn_equal_type.setOnClickListener {
            if (isEqual) {
                tv_check_title.text = "比较 oneLong 是否为长整型"
                //is用于判断是否等于某类型，对应的Java关键字是instanof
                val result = oneLong is Long
                tv_check_result.text = "is的比较结果是$result"
            } else {
                tv_check_title.text = "比较 oneLong 是否非长整型"
                //!is用于判断是否不等于某类型
                val result = oneLong !is Long
                tv_check_result.text = "!is的比较结果是$result"
            }
            isEqual = !isEqual
        }

        val oneArray:IntArray = intArrayOf(1, 2, 3, 4, 5)
        val four:Int = 4
        val nine:Int = 9
        btn_equal_item.setOnClickListener {
            when (count++%4) {
                0 -> {
                    tv_check_title.text = "比较 $four 是否存在数组oneArray中"
                    //in用于判断变量是否位于数组或容器中，Java判断数组中是否存在某元素只能采取循环遍历的方式
                    val result = four in oneArray
                    tv_check_result.text = "in的比较结果是$result"
                }
                1 -> {
                    tv_check_title.text = "比较 $four 是否不在数组oneArray中"
                    //!in用于判断变量是否不在数组或容器中
                    val result = four !in oneArray
                    tv_check_result.text = "!in的比较结果是$result"
                }
                2 -> {
                    tv_check_title.text = "比较 $nine 是否存在数组oneArray中"
                    //in用于判断变量是否位于数组或容器中
                    val result = nine in oneArray
                    tv_check_result.text = "in的比较结果是$result"
                }
                else -> {
                    tv_check_title.text = "比较 $nine 是否不在数组oneArray中"
                    //!in用于判断变量是否不在数组或容器中
                    val result = nine !in oneArray
                    tv_check_result.text = "!in的比较结果是$result"
                }
            }
        }

    }
}
