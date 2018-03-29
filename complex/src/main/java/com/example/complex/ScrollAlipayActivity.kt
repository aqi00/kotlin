package com.example.complex

import com.example.complex.adapter.RecyclerCommonAdapter
import com.example.complex.bean.LifeItem

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView

//因为布局文件通过include加载了多个子布局，所以Kotlin代码也要同时import导入所有相关的布局
import kotlinx.android.synthetic.main.activity_scroll_alipay.*
import kotlinx.android.synthetic.main.life_pay.*
import kotlinx.android.synthetic.main.toolbar_expand.*
import kotlinx.android.synthetic.main.toolbar_collapse.*

/**
 * Created by ouyangshen on 2017/9/3.
 */
class ScrollAlipayActivity : AppCompatActivity(), OnOffsetChangedListener {
    private var mMaskColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_alipay)
        mMaskColor = resources.getColor(R.color.blue_dark)
        //给控件abl_bar注册一个位置偏移的监听器
        abl_bar.addOnOffsetChangedListener(this)
        rv_content.layoutManager = GridLayoutManager(this, 4)
        //第一种方式：使用采取了LayoutContainer的适配器
        //rv_content.adapter = RecyclerLifeAdapter(this, LifeItem.default)
        //第二种方式：使用把三类可变要素抽象出来的适配器
        rv_content.adapter = RecyclerCommonAdapter(this, R.layout.item_life, LifeItem.default,
                { view, item ->
                    val iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
                    val tv_title = view.findViewById<TextView>(R.id.tv_title)
                    iv_pic.setImageResource(item.pic_id)
                    tv_title.text = item.title
                })
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val offset = Math.abs(verticalOffset)
        val total = appBarLayout.totalScrollRange
        val alphaOut = if (200 - offset < 0) 0 else 200 - offset
        //计算淡入时候的遮罩透明度
        val maskColorIn = Color.argb(offset, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor))
        //工具栏下方的频道布局要加速淡入或者淡出
        val maskColorInDouble = Color.argb(offset * 2, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor))
        //计算淡出时候的遮罩透明度
        val maskColorOut = Color.argb(alphaOut * 3, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor))
        if (offset <= total*0.45) { //偏移量小于一半，则显示展开时候的工具栏
            tl_expand.visibility = View.VISIBLE
            tl_collapse.visibility = View.GONE
            v_expand_mask.setBackgroundColor(maskColorInDouble)
        } else { //偏移量大于一半，则显示缩小时候的工具栏
            tl_expand.visibility = View.GONE
            tl_collapse.visibility = View.VISIBLE
            v_collapse_mask.setBackgroundColor(maskColorOut)
        }
        v_pay_mask.setBackgroundColor(maskColorIn)
    }

}
