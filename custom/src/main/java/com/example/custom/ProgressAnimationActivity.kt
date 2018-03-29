package com.example.custom

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_progress_animation.*

/**
 * Created by ouyangshen on 2017/9/17.
 */
class ProgressAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_animation)
        btn_animation.setOnClickListener {
            btn_animation.isEnabled = false
            //延迟50毫秒开始进度条动画
            handler.postDelayed(animation, 50)
        }
    }

    private var mProgress = 0
    private val handler = Handler()
    //定义一个刷新进度条的任务
    private val animation = object : Runnable {
        override fun run() {
            if (mProgress <= 100) {
                tpb_progress.progress = mProgress
                tpb_progress.progressText = "当前处理进度为$mProgress%"
                //当前进度未满100%，则继续进度刷新动画
                handler.postDelayed(this, 50)
                mProgress++
            } else {
                //进度条动画结束，恢复初始进度数值
                mProgress = 0
                btn_animation.isEnabled = true
            }
        }
    }

}
