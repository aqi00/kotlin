package com.example.simple

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import android.widget.Button

import kotlinx.android.synthetic.main.activity_button_click.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/8/27.
 */
class ButtonClickActivity : AppCompatActivity(), OnClickListener, OnLongClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_click)
        //点击事件第一种：匿名函数方式
        btn_click_anonymos.setOnClickListener { v ->
            //Kotlin对变量进行类型转换的关键字是as
            toast("您点击了控件：${(v as Button).text}")
        }
        btn_click_anonymos.setOnLongClickListener { v ->
            //Kotlin对变量进行类型转换的关键字是as
            longToast("您长按了控件：${(v as Button).text}")
            true
        }
        //点击事件第二种：内部类方式
        btn_click_inner.setOnClickListener(MyClickListener())
        btn_click_inner.setOnLongClickListener(MyLongClickListener())
        //点击事件第三种：Activity实现接口
        btn_click_interface.setOnClickListener(this)
        btn_click_interface.setOnLongClickListener(this)
    }
    //点击事件第三种：Activity实现接口
    override fun onClick(v: View) {
        if (v.id == R.id.btn_click_interface) {
            toast("您点击了控件：${(v as Button).text}")
        }
    }

    override fun onLongClick(v: View): Boolean {
        if (v.id == R.id.btn_click_interface) {
            longToast("您长按了控件：${(v as Button).text}")
        }
        return true
    }
    //点击事件第四种：XML布局中指定函数
    fun onButtonClick(v: View) {
        toast("您点击了控件：${(v as Button).text}")
    }
    //点击事件第二种：内部类方式
    private inner class MyClickListener : View.OnClickListener {
        override fun onClick(v: View) {
            toast("您点击了控件：${(v as Button).text}")
        }
    }

    private inner class MyLongClickListener : View.OnLongClickListener {
        override fun onLongClick(v: View): Boolean {
            longToast("您长按了控件：${(v as Button).text}")
            return true
        }
    }
}
