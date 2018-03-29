package com.example.complex

import com.example.complex.adapter.ChannelPagerAdapter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout.ViewPagerOnTabSelectedListener
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_department_channel.*

/**
 * Created by ouyangshen on 2017/9/3.
 */
class DepartmentChannelActivity : AppCompatActivity() {
    private val titles = mutableListOf<String>("服装", "电器")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_department_channel)
        tl_head.setBackgroundResource(R.color.pink)
        setSupportActionBar(tl_head)
        tl_head.setNavigationOnClickListener { finish() }
        initTabLayout()
        initTabViewPager()
    }

    private fun initTabLayout() {
        tab_title.addTab(tab_title.newTab().setText(titles[0]))
        tab_title.addTab(tab_title.newTab().setText(titles[1]))
        tab_title.addOnTabSelectedListener(ViewPagerOnTabSelectedListener(vp_content))
    }

    private fun initTabViewPager() {
        vp_content.adapter = ChannelPagerAdapter(supportFragmentManager, titles)
        vp_content.addOnPageChangeListener(object : SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                tab_title.getTabAt(position)!!.select()
            }
        })
    }

    public override fun onStart() {
        super.onStart()
        bgChangeReceiver = BgChangeReceiver()
        val filter = IntentFilter(ChannelPagerAdapter.EVENT)
        registerReceiver(bgChangeReceiver, filter)
    }

    public override fun onStop() {
        unregisterReceiver(bgChangeReceiver)
        super.onStop()
    }

    private var bgChangeReceiver: BgChangeReceiver? = null
    private inner class BgChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if (intent != null) {
                val color = intent.getIntExtra("color", Color.WHITE)
                tl_head.setBackgroundColor(color)
            }
        }
    }

}
