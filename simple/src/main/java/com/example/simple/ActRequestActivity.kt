package com.example.simple

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.simple.bean.MessageInfo

import com.example.simple.util.DateUtil

import kotlinx.android.synthetic.main.activity_act_request.*
import org.jetbrains.anko.startActivityForResult

/**
 * Created by ouyangshen on 2017/8/27.
 */
class ActRequestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_request)
        btn_act_request.setOnClickListener {
            val info = MessageInfo(et_request.text.toString(), DateUtil.nowTime)
            //ForResult表示需要返回参数
            startActivityForResult<ActResponseActivity>(0, "message" to info)
        }
    }

    //从下个页面返回到本页面时回调onActivityResult方法
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            //获得下一个页面的应答参数
            val response = data.extras.getParcelable<MessageInfo>("message")
            tv_request.text = "收到返回消息：\n应答时间为${response.send_time}\n应答内容为${response.content}"
        }
    }
}