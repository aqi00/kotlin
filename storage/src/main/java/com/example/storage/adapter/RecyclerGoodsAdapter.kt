package com.example.storage.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.example.storage.MainApplication
import com.example.storage.R
import com.example.storage.ShoppingDetailActivity

import com.example.storage.bean.GoodsInfo
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class RecyclerGoodsAdapter(context: Context, private val goods: MutableList<GoodsInfo>, private val mAddCartListener: addCartListener) : RecyclerBaseAdapter<RecyclerView.ViewHolder>(context) {

    override fun getItemCount(): Int = goods.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.item_recycler_goods, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vh: ItemHolder = holder as ItemHolder
        val info = goods[position]
        vh.iv_thumb.setImageBitmap(MainApplication.instance().mIconMap.get(info.rowid))
        vh.tv_name.text = info.name
        vh.tv_price.text = info.price.toString()
        vh.btn_add.setOnClickListener {
            mAddCartListener.addToCart(info.rowid)
            context.toast("已添加一部${info.name}到购物车")
        }
        // 列表项的点击事件需要自己实现
        vh.iv_thumb.setOnClickListener { v ->
            itemClickListener?.onItemClick(v, position)
        }
    }

    override fun onItemClick(view: View, position: Int) {
        val intent = context.intentFor<ShoppingDetailActivity>(
                "goods_id" to goods[position].rowid)
        context.startActivity(intent.clearTop())
    }

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_thumb = view.findViewById<ImageView>(R.id.iv_thumb)
        var tv_name = view.findViewById<TextView>(R.id.tv_name)
        var tv_price = view.findViewById<TextView>(R.id.tv_price)
        var btn_add = view.findViewById<Button>(R.id.btn_add)
    }

    interface addCartListener {
        fun addToCart(goods_id: Long)
    }

}
