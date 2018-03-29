package com.example.complex

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager

import com.example.complex.adapter.RecyclerCollapseAdapter

import kotlinx.android.synthetic.main.activity_image_fade.*

/**
 * Created by ouyangshen on 2017/9/3.
 */
class ImageFadeActivity : AppCompatActivity() {
    private val years = arrayOf("鼠年", "牛年", "虎年", "兔年", "龙年", "蛇年", "马年", "羊年", "猴年", "鸡年", "狗年", "猪年")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_fade)
        setSupportActionBar(tl_title)
        ctl_title.title = getString(R.string.toolbar_name)
        ctl_title.setExpandedTitleColor(Color.BLACK)
        ctl_title.setCollapsedTitleTextColor(Color.RED)
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = RecyclerCollapseAdapter(this, years)
    }
}
