package com.example.complex.adapter

import com.example.complex.R
import com.example.complex.bean.RecyclerInfo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/9/20.
 */
//把公共属性和公共方法剥离到基类RecyclerBaseAdapter，
//此处仅需实现getItemCount、onCreateViewHolder、onBindViewHolder三个方法，以及视图持有者的类定义
class RecyclerGridAdapter(context: Context, private val infos: MutableList<RecyclerInfo>) : RecyclerBaseAdapter<RecyclerView.ViewHolder>(context) {

    override fun getItemCount(): Int = infos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = inflater.inflate(R.layout.item_recycler_grid, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as ItemHolder
        vh.iv_pic.setImageResource(infos[position].pic_id)
        vh.tv_title.text = infos[position].title
        // 列表项的点击事件需要自己实现
        vh.ll_item.setOnClickListener { v ->
            itemClickListener?.onItemClick(v, position)
        }
        vh.ll_item.setOnLongClickListener { v ->
            itemLongClickListener?.onItemLongClick(v, position)
            true
        }
    }

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ll_item = view.findViewById<LinearLayout>(R.id.ll_item)
        var iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
        var tv_title = view.findViewById<TextView>(R.id.tv_title)
    }

    override fun onItemClick(view: View, position: Int) {
        val desc = "您点击了第${position+1}项，标题是${infos[position].title}"
        context.toast(desc)
    }

    override fun onItemLongClick(view: View, position: Int) {
        val desc = "您长按了第${position+1}项，标题是${infos[position].title}"
        context.toast(desc)
    }

}