package com.example.storage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.storage.util.Preference

import kotlinx.android.synthetic.main.activity_share_read.*

/**
 * Created by ouyangshen on 2017/9/10.
 */
class ShareReadActivity : AppCompatActivity() {
    private var name: String by Preference(this, "name", "")
    private var age: Int by Preference(this, "age", 0)
    private var height: Long by Preference(this, "height", 0)
    private var weight: Float by Preference(this, "weight", 0f)
    private var married: Boolean by Preference(this, "married", false)
    private var update_time: String by Preference(this, "update_time", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_read)
        val desc = "共享参数中保存的信息如下：" +
                    "\n　name的取值为$name" +
                    "\n　age的取值为$age" +
                    "\n　height的取值为$height" +
                    "\n　weight的取值为$weight" +
                    "\n　married的取值为$married" +
                    "\n　update_time的取值为$update_time"
        tv_share.text = desc
    }

}
