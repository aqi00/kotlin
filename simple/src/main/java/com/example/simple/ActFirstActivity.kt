package com.example.simple

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.example.simple.util.DateUtil

import kotlinx.android.synthetic.main.activity_act_first.*
import org.jetbrains.anko.*

/**
 * Created by ouyangshen on 2017/8/27.
 */
class ActFirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_first)
        btn_act_request.setOnClickListener {
            //第一种写法，参数名和参数值使用关键字to隔开
            startActivity<ActSecondActivity>(
                    "request_time" to DateUtil.nowTime,
                    "request_content" to et_request.text.toString())
            //第二种写法，利用Pair把参数名和参数值进行配对
//            startActivity<ActSecondActivity>(
//                    Pair("request_time", DateUtil.nowTime),
//                    Pair("request_content", et_request.text.toString()))
        }

        btn_act_flag.setOnClickListener {
            val intent = intentFor<ActSecondActivity>(
                    "request_time" to DateUtil.nowTime,
                    "request_content" to et_request.text.toString())
            startActivity(intent.newTask() //对应启动标志FLAG_ACTIVITY_NEW_TASK
                    //.singleTop() //对应启动标志FLAG_ACTIVITY_SINGLE_TOP
                    //.clearTop() //对应启动标志FLAG_ACTIVITY_CLEAR_TOP
                    //.noHistory() //对应启动标志FLAG_ACTIVITY_NO_HISTORY
                    //.clearTask() //对应启动标志FLAG_ACTIVITY_CLEAR_TASK
            )
        }
    }
}