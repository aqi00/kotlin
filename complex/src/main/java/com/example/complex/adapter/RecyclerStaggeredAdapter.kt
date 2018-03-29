package com.example.complex.adapter

import com.example.complex.R
import com.example.complex.bean.RecyclerInfo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_recycler_staggered.*

//利用Kotlin的插件LayoutContainer，在适配器中直接使用控件对象，而无需对其进行显式声明
class RecyclerStaggeredAdapter(context: Context, private val infos: MutableList<RecyclerInfo>) : RecyclerBaseAdapter<RecyclerView.ViewHolder>(context) {

    override fun getItemCount(): Int = infos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = inflater.inflate(R.layout.item_recycler_staggered, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemHolder).bind(infos[position])
    }

    //注意这里要去掉inner，否则运行报错“java.lang.NoSuchMethodError: No virtual method _$_findCachedViewById”
    class ItemHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: RecyclerInfo) {
            //因为运用了插件LayoutContainer，所以这里可以直接使用控件对象
            iv_pic.setImageResource(item.pic_id)
            tv_title.text = item.title
        }
    }

}
