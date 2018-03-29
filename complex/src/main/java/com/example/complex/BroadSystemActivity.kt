package com.example.complex

import com.example.complex.util.DateUtil

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

import kotlinx.android.synthetic.main.activity_broadcast_system.*

/**
 * Created by ouyangshen on 2017/9/3.
 */
class BroadSystemActivity : AppCompatActivity() {
    var desc = "开始侦听分钟广播，请稍等。注意要保持屏幕亮着，才能正常收到广播"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_system)
        tv_system.text = desc
    }

    override fun onStart() {
        super.onStart()
        timeReceiver = TimeReceiver()
        //声明一个过滤器，只接收名称为Intent.ACTION_TIME_TICK的分钟广播
        val filter = IntentFilter(Intent.ACTION_TIME_TICK)
        //在活动启动时注册广播接收器
        registerReceiver(timeReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        //在活动停止时注销广播接收器
        unregisterReceiver(timeReceiver)
    }

    private var timeReceiver: TimeReceiver? = null
    //定义一个时间广播的接收器
    inner class TimeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if (intent != null) {
                desc = "$desc\n${DateUtil.nowTime} 收到一个${intent.action}广播"
                tv_system.text = desc
            }
        }
    }

}
