package com.example.complex

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_toolbar.*

/**
 * Created by ouyangshen on 2017/9/3.
 */
class ToolbarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)
        //设置工具栏的主标题文本内容
        tl_head.title = "这是工具栏的主标题"
        //设置工具栏的主标题文本颜色
        tl_head.setTitleTextColor(Color.RED)
        //设置工具栏左边的Logo图标
        tl_head.setLogo(R.drawable.ic_launcher)
        //设置工具栏的副标题文本内容
        tl_head.subtitle = "这是副标题"
        //设置工具栏的副标题文本颜色
        tl_head.setSubtitleTextColor(Color.YELLOW)
        //设置工具栏的背景
        tl_head.setBackgroundResource(R.color.blue_light)
        //使用Toolbar替换系统自带的ActionBar
        setSupportActionBar(tl_head)
        //工具栏最左侧的导航图标，通常用作返回按钮
        tl_head.setNavigationIcon(R.drawable.ic_back)
        //最左侧导航图标的点击事件，即返回上一个页面
        //该方法必须放到setSupportActionBar之后，不然不起作用
        tl_head.setNavigationOnClickListener { finish() }
    }

}
