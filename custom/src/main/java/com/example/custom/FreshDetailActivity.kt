package com.example.custom

import com.example.custom.bean.FreshInfo
import com.example.custom.service.GroupService

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_fresh_detail.*
import org.jetbrains.anko.startService

/**
 * Created by ouyangshen on 2017/9/17.
 */
class FreshDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fresh_detail)
        val freshInfo: FreshInfo = intent.getParcelableExtra("fresh")
        tl_head.title = freshInfo.name
        setSupportActionBar(tl_head)
        tl_head.setNavigationOnClickListener { finish() }

        iv_icon.setImageResource(freshInfo.imageId)
        tv_desc.text = freshInfo.desc
        tv_price.text = "团购价格：${freshInfo.price}"
        count = freshInfo.peopleCount
        handler.postDelayed(animation, 50)

        if (freshInfo.isJoin) {
            btn_group.isEnabled = false
            btn_group.text = "我已参加团购"
        } else {
            btn_group.setOnClickListener {
                handler.removeCallbacks(animation)
                handler.postDelayed({
                    tpb_count.progress = (count+1)*100/TOTAL
                    tpb_count.progressText = "当前已有${count+1}人加入团购"
                    tpb_count.invalidate()
                }, 100)
                freshInfo.isJoin = true
                btn_group.isEnabled = false
                btn_group.text = "我已参加团购"
                MainApplication.instance().groupMap.put(freshInfo.name, freshInfo.isJoin)
                startService<GroupService>("fresh" to freshInfo)
            }
        }
    }

    private var count: Int = 0
    private val TOTAL = 1000
    private var mProgress = 0
    private val handler = Handler()
    private val animation = object : Runnable {
        override fun run() {
            if (mProgress <= count*100/TOTAL) {
                tpb_count.progress = mProgress
                tpb_count.progressText = "当前已有${mProgress*TOTAL/100}人加入团购"
                mProgress ++
                handler.postDelayed(this, 50)
            }
        }
    }

}
