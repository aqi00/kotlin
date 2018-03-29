package com.example.network

import com.example.network.bean.UserInfo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_json_convert.*

class JsonConvertActivity : AppCompatActivity() {
    private val user = UserInfo(name="阿四", age=25, height=160L, weight=45.0f, married=false)
    //把数据类的对象直接转换成json格式
    private val json = Gson().toJson(user)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json_convert)
        btn_origin_json.setOnClickListener { tv_json.text = "json串内容如下：\n$json" }
        btn_convert_json.setOnClickListener {
            //利用Gson包直接将json串解析为对应格式的数据类对象
            val newUser = Gson().fromJson(json, UserInfo::class.java)
            tv_json.text = "从json串解析而来的用户信息如下：" +
                    "\n\t姓名=${newUser.name}" +
                    "\n\t年龄=${newUser.age}" +
                    "\n\t身高=${newUser.height}" +
                    "\n\t体重=${newUser.weight}" +
                    "\n\t婚否=${newUser.married}"
        }
    }

}
