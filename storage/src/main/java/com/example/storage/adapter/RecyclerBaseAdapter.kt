package com.example.storage.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by ouyangshen on 2017/9/20.
 */
abstract class RecyclerBaseAdapter<VH : RecyclerView.ViewHolder>(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), RecyclerExtras.OnItemClickListener, RecyclerExtras.OnItemLongClickListener {
    val inflater: LayoutInflater = LayoutInflater.from(context)

    override abstract fun getItemCount(): Int

    override abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    override abstract fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)

    override fun getItemViewType(position: Int): Int = 0

    override fun getItemId(position: Int): Long = position.toLong()

    var itemClickListener: RecyclerExtras.OnItemClickListener? = null
    fun setOnItemClickListener(listener: RecyclerExtras.OnItemClickListener) {
        this.itemClickListener = listener
    }

    var itemLongClickListener: RecyclerExtras.OnItemLongClickListener? = null
    fun setOnItemLongClickListener(listener: RecyclerExtras.OnItemLongClickListener) {
        this.itemLongClickListener = listener
    }

    var itemDeleteClickListener: RecyclerExtras.OnItemDeleteClickListener? = null
    fun setOnItemDeleteClickListener(listener: RecyclerExtras.OnItemDeleteClickListener) {
        this.itemDeleteClickListener = listener
    }

    override fun onItemClick(view: View, position: Int) {}

    override fun onItemLongClick(view: View, position: Int) {}

}