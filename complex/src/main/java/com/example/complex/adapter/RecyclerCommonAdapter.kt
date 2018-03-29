package com.example.complex.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by ouyangshen on 2017/9/24.
 */
//循环视图通用适配器
//将具体业务中会变化的三类要素抽取出来，作为外部传进来的变量。这三类要素包括：
//布局文件对应的资源编号、列表项的数据结构、各个控件对象的初始化操作
class RecyclerCommonAdapter<T>(context: Context, private val layoutId: Int, private val items: List<T>, val init: (View, T) -> Unit): RecyclerBaseAdapter<RecyclerCommonAdapter.ItemHolder<T>>(context) {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = inflater.inflate(layoutId, parent, false)
        return ItemHolder<T>(view, init)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh: ItemHolder<T> = holder as ItemHolder<T>
        vh.bind(items.get(position))
    }

    class ItemHolder<in T>(val view: View, val init: (View, T) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(item: T) {
            init(view, item)
        }
    }
}