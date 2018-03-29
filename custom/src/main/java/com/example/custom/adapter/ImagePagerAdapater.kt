package com.example.custom.adapter

import com.example.custom.bean.GoodsInfo

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.ImageView
import android.widget.ImageView.ScaleType

class ImagePagerAdapater(private val context: Context, private val goodsList: MutableList<GoodsInfo>) : PagerAdapter() {
    private val viewList = mutableListOf<ImageView>()

    init {
        for (item in goodsList) {
            val view = ImageView(context)
            view.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            view.setImageResource(item.pic)
            view.scaleType = ScaleType.FIT_CENTER
            viewList.add(view)
        }
    }

    override fun getCount(): Int = viewList.size

    override fun isViewFromObject(arg0: View, arg1: Any): Boolean = arg0 === arg1

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(viewList[position])
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(viewList[position])
        return viewList[position]
    }

    override fun getPageTitle(position: Int): CharSequence = goodsList[position].name

}
