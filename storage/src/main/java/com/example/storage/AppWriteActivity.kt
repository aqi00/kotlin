package com.example.storage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import com.example.storage.util.DateUtil

import kotlinx.android.synthetic.main.activity_app_write.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/9/10.
 */
class AppWriteActivity : AppCompatActivity() {
    private val types = listOf("未婚", "已婚")
    private var bMarried = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_write)

        sp_married.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = types[0]
        tv_spinner.setOnClickListener {
            selector("请选择婚姻状况", types) { i ->
                tv_spinner.text = types[i]
                bMarried = if (i == 0) false else true
            }
        }
        btn_save.setOnClickListener {
            when {
                et_name.text.isEmpty() -> toast("请先填写姓名")
                et_age.text.isEmpty() -> toast("请先填写年龄")
                et_height.text.isEmpty() -> toast("请先填写身高")
                et_weight.text.isEmpty() -> toast("请先填写体重")
                else -> {
                    val app = MainApplication.instance()
                    app.mInfoMap.put("name", et_name.text.toString())
                    app.mInfoMap.put("age", et_age.text.toString())
                    app.mInfoMap.put("height", et_height.text.toString())
                    app.mInfoMap.put("weight", et_weight.text.toString())
                    app.mInfoMap.put("married", types[if (!bMarried) 0 else 1])
                    app.mInfoMap.put("update_time", DateUtil.nowDateTime)
                    toast("数据已写入全局内存")
                }
            }
        }
    }

}
