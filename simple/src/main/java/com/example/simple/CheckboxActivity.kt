package com.example.simple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_checkbox.*

/**
 * Created by ouyangshen on 2017/8/27.
 */
class CheckboxActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkbox)
        ck_select.isChecked = false
        ck_select.setOnCheckedChangeListener { buttonView, isChecked ->
            tv_select.text = "您${ if (isChecked) "勾选" else "取消勾选"}了复选框"
        }
    }
}
