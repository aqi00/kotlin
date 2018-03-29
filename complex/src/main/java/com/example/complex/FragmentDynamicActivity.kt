package com.example.complex

import com.example.complex.adapter.MobilePagerAdapter
import com.example.complex.bean.GoodsInfo

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.TypedValue

import kotlinx.android.synthetic.main.activity_fragment_dynamic.*

/**
 * Created by ouyangshen on 2017/9/3.
 */
class FragmentDynamicActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_dynamic)
        pts_tab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        //碎片适配器需要传入碎片管理器对象supportFragmentManager
        vp_content.adapter = MobilePagerAdapter(supportFragmentManager, GoodsInfo.defaultList)
        vp_content.currentItem = 0
    }
}
