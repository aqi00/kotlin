package com.example.complex

import com.example.complex.adapter.RecyclerStaggeredAdapter
import com.example.complex.bean.RecyclerInfo
import com.example.complex.widget.SpacesItemDecoration

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.LinearLayout

import kotlinx.android.synthetic.main.activity_recycler_staggered.*

/**
 * Created by ouyangshen on 2017/9/3.
 */
class RecyclerStaggeredActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_staggered)

        rv_staggered.layoutManager = StaggeredGridLayoutManager(3, LinearLayout.VERTICAL)
        //第一种方式：使用采取了LayoutContainer的插件适配器
        val adapter = RecyclerStaggeredAdapter(this, RecyclerInfo.defaultStag)
        //第二种方式：使用把三类可变要素抽象出来的通用适配器
//        val adapter = RecyclerCommonAdapter(this, R.layout.item_recycler_staggered, RecyclerInfo.defaultStag,
//                {view, item ->
//                    val iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
//                    val tv_title = view.findViewById<TextView>(R.id.tv_title)
//                    iv_pic.setImageResource(item.pic_id)
//                    tv_title.text = item.title
//                })
        rv_staggered.adapter = adapter
        rv_staggered.itemAnimator = DefaultItemAnimator()
        rv_staggered.addItemDecoration(SpacesItemDecoration(3))
    }

}
