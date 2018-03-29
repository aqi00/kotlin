package com.example.custom

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_runnable.*

/**
 * Created by ouyangshen on 2017/9/17.
 */
class RunnableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_runnable)
        var isStarted = false
        btn_runnable.setOnClickListener {
            isStarted = !isStarted
            if (isStarted) {
                btn_runnable.text = "停止计数"
                //handler.post(Counter())
                handler.post(counter)
                //第四种：匿名实例方式（又分三小点）
                //第1点：在post方法中直接填写Runnable对象的定义代码
//                handler.post(Runnable {
//                    count++
//                    tv_result.text = "当前计数值为：$count"
//                })
                //第2点：如果该任务只需执行一次，则可采用匿名实例的方式，直接嵌入任务的执行代码
//                handler.post {
//                    count++
//                    tv_result.text = "当前计数值为：$count"
//                }
                //第3点：如果是延迟执行任务，则可将匿名实例作为postDelayed的输入参数
//                handler.postDelayed({
//                    count++
//                    tv_result.text = "当前计数值为：$count"
//                }, 1000)
            } else {
                btn_runnable.text = "开始计数"
                handler.removeCallbacks(counter)
            }
        }
    }

    private val handler = Handler()
    private var count = 0

    //第一种：内部类方式
    inner private class Counter : Runnable {
        override fun run() {
            count++
            tv_result.text = "当前计数值为：$count"
            handler.postDelayed(this, 1000)
        }
    }

    //第二种：匿名内部类方式
    private val counter = object : Runnable {
        override fun run() {
            count++
            tv_result.text = "当前计数值为：$count"
            handler.postDelayed(this, 1000)
        }
    }

    //第三种：匿名函数方式
//    private val counter = Runnable {
//        count++
//        tv_result.text = "当前计数值为：$count"
//    }

    //Runnable对象的三种声明方式：
    //1、带继承的完整的声明，适用于内部调用自身（使用了关键字this）
    //2、匿名类的声明，内部不可使用this表达自身，适用于外部多次调用该任务
    //3、匿名实例的声明，内部不可使用this表达自身，外部也只能调用一次

}
