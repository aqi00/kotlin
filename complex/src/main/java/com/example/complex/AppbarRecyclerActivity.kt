package com.example.complex

import com.example.complex.adapter.RecyclerCollapseAdapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_appbar_recycler.*

class AppbarRecyclerActivity : AppCompatActivity() {
    private val yearArray = arrayOf("鼠年", "牛年", "虎年", "兔年", "龙年", "蛇年", "马年", "羊年", "猴年", "鸡年", "狗年", "猪年")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appbar_recycler)
        setSupportActionBar(tl_title)
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = RecyclerCollapseAdapter(this, yearArray)
    }

}

