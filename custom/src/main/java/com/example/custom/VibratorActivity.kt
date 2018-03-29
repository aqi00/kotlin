package com.example.custom

import com.example.custom.util.DateUtil
import com.example.custom.util.vibrator

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.activity_vibrator.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/9/17.
 */
class VibratorActivity : AppCompatActivity() {
    private val durations = listOf(500, 1000, 2000, 3000, 4000, 5000)
    private val descs = listOf("0.5秒", "1秒", "2秒", "3秒", "4秒", "5秒")
    private var interval: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vibrator)
        btn_start.setOnClickListener {
            //常规做法：从系统服务中获取震动器对象
//            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//            vibrator.vibrate(3000)
            //利用扩展函数获得震动器对象
//            getVibrator().vibrate(interval.toLong())
            //利用扩展函数实现扩展属性，直接使用vibrator即可指代震动器对象
            vibrator.vibrate(interval.toLong())
            tv_vibrator.text = "${DateUtil.nowTime} 手机震动了${interval/1000.0f}秒"
        }

        sp_duration.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = descs[0]
        tv_spinner.setOnClickListener {
            selector("请选择震动时长", descs) { i ->
                tv_spinner.text = descs[i]
                interval = durations[i]
            }
        }
    }

}
