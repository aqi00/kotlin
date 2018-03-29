package com.example.hello

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_easy.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

class EasyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy)

        //tv_hello.setText("你好呀")
        tv_hello.text = "你好呀"

        btn_click.setOnClickListener { btn_click.text="您点了一下下" }
        btn_click_long.setOnLongClickListener { btn_click_long.text="您长按了一小会"; true }

        btn_toast.setOnClickListener { toast("小提示：您点了一下下") }
        btn_toast_long.setOnLongClickListener { longToast("长提示：您长按了一小会"); true }
    }
}
