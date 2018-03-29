package com.example.complex

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout.LayoutParams
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View

import com.example.complex.adapter.RecyclerCollapseAdapter

import kotlinx.android.synthetic.main.activity_scroll_flag.*
import org.jetbrains.anko.selector

/**
 * Created by ouyangshen on 2017/9/3.
 */
class ScrollFlagActivity : AppCompatActivity() {
    private val years = arrayOf("鼠年", "牛年", "虎年", "兔年", "龙年", "蛇年", "马年", "羊年", "猴年", "鸡年", "狗年", "猪年")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_flag)
        tl_title.setBackgroundColor(Color.YELLOW)
        setSupportActionBar(tl_title)
        ctl_title.title = "滚动标志"
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = RecyclerCollapseAdapter(this, years)
        initFlagSpinner()
    }

    private val descs = listOf("scroll", "scroll|enterAlways", "scroll|exitUntilCollapsed", "scroll|enterAlways|enterAlwaysCollapsed", "scroll|snap")
    private val flags = intArrayOf(LayoutParams.SCROLL_FLAG_SCROLL, LayoutParams.SCROLL_FLAG_SCROLL or LayoutParams.SCROLL_FLAG_ENTER_ALWAYS, LayoutParams.SCROLL_FLAG_SCROLL or LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED, LayoutParams.SCROLL_FLAG_SCROLL or LayoutParams.SCROLL_FLAG_ENTER_ALWAYS or LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED, LayoutParams.SCROLL_FLAG_SCROLL or LayoutParams.SCROLL_FLAG_SNAP)
    private fun initFlagSpinner() {
        sp_flag.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = descs[0]
        tv_spinner.setOnClickListener {
            selector("请选择滚动标志", descs) { i ->
                tv_spinner.text = descs[i]
                val params = ctl_title.layoutParams as LayoutParams
                params.scrollFlags = flags[i]
            }
        }
    }

}
