package com.example.custom

import com.example.custom.adapter.ImagePagerAdapater
import com.example.custom.bean.GoodsInfo

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_custom_property.*
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/9/17.
 */
class CustomPropertyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_property)
        val goodsList = GoodsInfo.defaultList
        vp_content.adapter = ImagePagerAdapater(this, goodsList)
        vp_content.currentItem = 0
        vp_content.addOnPageChangeListener(
                object: ViewPager.SimpleOnPageChangeListener() {
                    override fun onPageSelected(arg0: Int) {
                        toast("您翻到的手机品牌是：${goodsList[arg0].name}")
                    }
                })
    }

}
