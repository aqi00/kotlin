package com.example.complex

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue

import com.example.complex.adapter.ImagePagerAdapater
import com.example.complex.bean.GoodsInfo

import kotlinx.android.synthetic.main.activity_view_pager.*
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/9/3.
 */
class ViewPagerActivity : AppCompatActivity(), OnPageChangeListener {
    private var goodsList = GoodsInfo.defaultList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        //注意PagerTabStrip不存在textSize属性，只能调用setTextSize方法设置文字大小
        pts_tab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        pts_tab.setTextColor(Color.GREEN)
        vp_content.adapter = ImagePagerAdapater(this, goodsList)
        vp_content.currentItem = 0
        vp_content.addOnPageChangeListener(this)
    }

    override fun onPageScrollStateChanged(arg0: Int) {}

    override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}

    //在页面切换结束（即滑动停止）时触发该方法
    override fun onPageSelected(arg0: Int) {
        toast("您翻到的手机品牌是：${goodsList[arg0].name}")
    }

}
