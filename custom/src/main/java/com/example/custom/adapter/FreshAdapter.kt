package com.example.custom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.AdapterView.OnItemClickListener

import com.example.custom.FreshDetailActivity
import com.example.custom.MainApplication
import com.example.custom.R
import com.example.custom.bean.FreshInfo

import org.jetbrains.anko.startActivity

class FreshAdapter(private val context: Context, private val freshList: MutableList<FreshInfo>) : BaseAdapter(), OnItemClickListener {

    override fun getCount(): Int = freshList.size

    override fun getItem(position: Int): Any = freshList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View
        var holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_fresh, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }
        val Fresh = freshList[position]
        holder.iv_icon.setImageResource(Fresh.imageId)
        holder.tv_name.text = Fresh.name
        holder.tv_desc.text = Fresh.desc
        holder.tv_price.text = "价格：${Fresh.price}元"
        holder.tv_count.text = "已有${Fresh.peopleCount}人参团"
        return view
    }

    inner class ViewHolder(var view: View) {
        var iv_icon: ImageView = view.findViewById<ImageView>(R.id.iv_icon)
        var tv_name: TextView = view.findViewById<TextView>(R.id.tv_name)
        var tv_desc: TextView = view.findViewById<TextView>(R.id.tv_desc)
        var tv_price: TextView = view.findViewById<TextView>(R.id.tv_price)
        var tv_count: TextView = view.findViewById<TextView>(R.id.tv_count)
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val fresh = freshList[position]
        if (MainApplication.instance().groupMap.containsKey(fresh.name)) {
            fresh.isJoin = MainApplication.instance().groupMap[fresh.name]!!
        }
        context.startActivity<FreshDetailActivity>("fresh" to fresh)
    }

}
