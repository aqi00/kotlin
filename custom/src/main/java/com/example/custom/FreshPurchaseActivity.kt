package com.example.custom

import com.example.custom.adapter.FreshAdapter
import com.example.custom.bean.FreshInfo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_fresh_purchase.*

/**
 * Created by ouyangshen on 2017/9/17.
 */
class FreshPurchaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fresh_purchase)
        tl_head.title = "生鲜团购"
        setSupportActionBar(tl_head)
        tl_head.setNavigationOnClickListener { finish() }

        initCrabList()
        initShrimpList()
        initFishList()
    }

    private fun initCrabList() {
        val freshList = mutableListOf<FreshInfo>(
                FreshInfo(name = "阳澄湖大闸蟹",
                        desc = "产自阳澄湖的天然大闸蟹，口味一流，认准阳澄湖。",
                        imageId = R.drawable.dazhaxie,
                        price = 999, peopleCount = 500 ),
                FreshInfo(name = "平潭红鲟",
                        desc = "膏红肉肥的锯缘青蟹，滋补强身，平潭特产。",
                        imageId = R.drawable.hongxun,
                        price = 666, peopleCount = 500 ),
                FreshInfo(name = "阿拉斯加帝王蟹",
                        desc = "来自大自然的馈赠，阿拉斯加当地深海捕捞。",
                        imageId = R.drawable.diwangxie,
                        price = 888, peopleCount = 500 ) )
        val adapter = FreshAdapter(this, freshList)
        nslv_crab.adapter = adapter
        nslv_crab.onItemClickListener = adapter
    }

    private fun initShrimpList() {
        val freshList = mutableListOf<FreshInfo>(
                FreshInfo(name = "盱眙小龙虾",
                        desc = "正宗盱眙养殖的小龙虾，麻辣鲜香，夜宵好伴侣。",
                        imageId = R.drawable.xiaolongxia,
                        price = 999, peopleCount = 500 ),
                FreshInfo(name = "青岛皮皮虾",
                        desc = "虾蛄、撒尿虾、富贵虾，富贵之人专享美食。",
                        imageId = R.drawable.pipixia,
                        price = 666, peopleCount = 500 ),
                FreshInfo(name = "波士顿大龙虾",
                        desc = "舌尖上的美味，肉质鲜嫩，唯有波士顿大龙虾。",
                        imageId = R.drawable.dalongxia,
                        price = 888, peopleCount = 500 ) )
        val adapter = FreshAdapter(this, freshList)
        nslv_shrimp.adapter = adapter
        nslv_shrimp.onItemClickListener = adapter
    }

    private fun initFishList() {
        val freshList = mutableListOf<FreshInfo>(
                FreshInfo(name = "西塞山桂花鱼",
                        desc = "源于唐代的佳肴，西塞山前白鹭飞，桃花流水鳜鱼肥。",
                        imageId = R.drawable.guihuayu,
                        price = 999, peopleCount = 500 ),
                FreshInfo(name = "武昌团头鲂",
                        desc = "伟人留诗篇：才饮长江水，又食武昌鱼。",
                        imageId = R.drawable.wuchangyu,
                        price = 666, peopleCount = 500 ),
                FreshInfo(name = "挪威三文鱼",
                        desc = "北欧洁净海域捕捞，全程冷链，新鲜直达。",
                        imageId = R.drawable.sanwenyu,
                        price = 888, peopleCount = 500 ) )
        val adapter = FreshAdapter(this, freshList)
        nslv_fish.adapter = adapter
        nslv_fish.onItemClickListener = adapter
    }

}
