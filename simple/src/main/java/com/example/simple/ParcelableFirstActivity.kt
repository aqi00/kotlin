package com.example.simple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.simple.bean.MessageInfo
import com.example.simple.util.DateUtil

import kotlinx.android.synthetic.main.activity_parcelable_first.*
import org.jetbrains.anko.startActivity

/**
 * Created by ouyangshen on 2017/8/27.
 */
class ParcelableFirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelable_first)
        btn_act_request.setOnClickListener {
            val request = MessageInfo(et_request.text.toString(), DateUtil.nowTime)
            startActivity<ParcelableSecondActivity>("message" to request)
        }

    }
}
