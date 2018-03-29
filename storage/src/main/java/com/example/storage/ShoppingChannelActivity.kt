package com.example.storage

import com.example.storage.adapter.RecyclerGoodsAdapter
import com.example.storage.adapter.RecyclerGoodsAdapter.addCartListener
import com.example.storage.bean.CartInfo
import com.example.storage.database.CartDBHelper
import com.example.storage.database.GoodsDBHelper
import com.example.storage.util.DateUtil
import com.example.storage.widget.SpacesItemDecoration

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import com.example.storage.util.Preference

import kotlinx.android.synthetic.main.toolbar_custom.*
import kotlinx.android.synthetic.main.activity_shopping_channel.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor

/**
 * Created by ouyangshen on 2017/9/10.
 */
class ShoppingChannelActivity : AppCompatActivity(), addCartListener {
    private var mCount: Int by Preference(this, "count", 0)
    private lateinit var mGoodsHelper: GoodsDBHelper
    private lateinit var mCartHelper: CartDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_channel)
        tv_title.text = "手机商场"
        setSupportActionBar(tl_head)
        tl_head.setNavigationOnClickListener { finish() }

        mGoodsHelper = GoodsDBHelper.getInstance(this)
        mCartHelper = CartDBHelper.getInstance(this)
        iv_cart.setOnClickListener {
            startActivity(intentFor<ShoppingCartActivity>().clearTop())
        }
    }

    override fun onResume() {
        super.onResume()
        showGoods() //展示商品列表
    }

    override fun addToCart(goods_id: Long) {
        mCount++
        tv_count.text = mCount.toString()
        var info = mCartHelper.queryByGoodsId(goods_id)
        if (info.goods_id > 0) {
            //数据库已有商品记录，那么数量加一。否则新增一条记录。
            info.count++
            info.update_time = DateUtil.getFormatTime()
            mCartHelper.update(info)
        } else {
            info = CartInfo(count=1, goods_id=goods_id, update_time=DateUtil.getFormatTime())
            mCartHelper.insert(info)
        }
    }

    private fun showGoods() {
        tv_count.text = mCount.toString()
        if (MainApplication.instance().mIconMap.isEmpty()) {
            ShoppingCartActivity.downloadGoods(this, "false", mGoodsHelper)
        }
        rv_channel.layoutManager = GridLayoutManager(this, 2)
        val goodsArray = mGoodsHelper.queryAll()
        val adapter = RecyclerGoodsAdapter(this, goodsArray, this)
        rv_channel.adapter = adapter
        adapter.setOnItemClickListener(adapter)
        rv_channel.itemAnimator = DefaultItemAnimator()
        rv_channel.addItemDecoration(SpacesItemDecoration(2))
    }

}
