package com.example.storage

import com.example.storage.util.DateUtil

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.storage.util.Preference

import kotlinx.android.synthetic.main.activity_share_write.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/9/10.
 */
class ShareWriteActivity : AppCompatActivity() {
    private val types = listOf("未婚", "已婚")
    private var bMarried = false
    private var name: String by Preference(this, "name", "")
    private var age: Int by Preference(this, "age", 0)
    private var height: Long by Preference(this, "height", 0)
    private var weight: Float by Preference(this, "weight", 0f)
    private var married: Boolean by Preference(this, "married", false)
    private var update_time: String by Preference(this, "update_time", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_write)

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
                    name = et_name.text.toString()
                    age = et_age.text.toString().toInt()
                    height = et_height.text.toString().toLong()
                    weight = et_weight.text.toString().toFloat()
                    married = bMarried
                    update_time = DateUtil.nowDateTime
                    toast("数据已写入共享参数")
                }
            }
        }
    }

}
