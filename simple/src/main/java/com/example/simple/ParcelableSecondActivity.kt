package com.example.simple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.simple.bean.MessageInfo

import kotlinx.android.synthetic.main.activity_parcelable_second.*

/**
 * Created by ouyangshen on 2017/8/27.
 */
class ParcelableSecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelable_second)
        //获得Parcelable格式的请求参数
        val request = intent.extras.getParcelable<MessageInfo>("message")
        tv_response.text = "收到打包好的请求消息：\n请求时间为${request.send_time}\n请求内容为${request.content}"
    }
}
