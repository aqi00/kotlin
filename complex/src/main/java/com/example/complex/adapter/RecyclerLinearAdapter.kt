package com.example.complex.adapter

import com.example.complex.R
import com.example.complex.bean.RecyclerInfo
import com.example.complex.adapter.RecyclerExtras.OnItemClickListener
import com.example.complex.adapter.RecyclerExtras.OnItemLongClickListener

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.toast

//ViewHolder在构造时初始化布局中的控件对象
class RecyclerLinearAdapter(private val context: Context, private val infos: MutableList<RecyclerInfo>) : RecyclerView.Adapter<ViewHolder>(), OnItemClickListener, OnItemLongClickListener {
    val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItemCount(): Int = infos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.item_recycler_linear, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vh: ItemHolder = holder as ItemHolder
        vh.iv_pic.setImageResource(infos[position].pic_id)
        vh.tv_title.text = infos[position].title
        vh.tv_desc.text = infos[position].desc

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
        var tv_desc = view.findViewById<TextView>(R.id.tv_desc)
    }

    private var itemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

    private var itemLongClickListener: OnItemLongClickListener? = null
    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        this.itemLongClickListener = listener
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
