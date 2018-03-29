package com.example.network.adapter

import com.example.network.R
import com.example.network.bean.RecyclerInfo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class RecyclerGridAdapter(private val context: Context, private val infos: MutableList<RecyclerInfo>) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int = infos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recycler_grid, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vh: ItemHolder = holder as ItemHolder
        vh.iv_pic.setImageResource(infos[position].pic_id)
        vh.tv_title.text = infos[position].title
    }

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
        var tv_title = view.findViewById<TextView>(R.id.tv_title)
    }

}
