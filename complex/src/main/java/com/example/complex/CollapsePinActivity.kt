package com.example.complex

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager

import com.example.complex.adapter.RecyclerCollapseAdapter

import kotlinx.android.synthetic.main.activity_collapse_pin.*

/**
 * Created by ouyangshen on 2017/9/3.
 */
class CollapsePinActivity : AppCompatActivity() {
    private val years = arrayOf("鼠年", "牛年", "虎年", "兔年", "龙年", "蛇年", "马年", "羊年", "猴年", "鸡年", "狗年", "猪年")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collapse_pin)
        tl_title.setBackgroundColor(Color.RED)
        setSupportActionBar(tl_title)
        ctl_title.title = getString(R.string.toolbar_name)
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = RecyclerCollapseAdapter(this, years)
    }
}
