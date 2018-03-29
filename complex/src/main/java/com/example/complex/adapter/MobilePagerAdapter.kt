package com.example.complex.adapter

import com.example.complex.bean.GoodsInfo
import com.example.complex.fragment.DynamicFragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class MobilePagerAdapter(fm: FragmentManager, private val goodsList: MutableList<GoodsInfo>) : FragmentStatePagerAdapter(fm) {

    //获取页面的数量
    override fun getCount(): Int = goodsList.size

    //获取每个页面的碎片对象
    override fun getItem(position: Int): Fragment {
        val item = goodsList[position]
        return DynamicFragment.newInstance(position, item.pic, item.desc, item.price)
    }

    //获取页面的标题
    override fun getPageTitle(position: Int): CharSequence = goodsList[position].name

}

