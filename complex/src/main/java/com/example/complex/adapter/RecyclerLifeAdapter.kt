package com.example.complex.adapter

import com.example.complex.R
import com.example.complex.bean.LifeItem

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_life.*

class RecyclerLifeAdapter(context: Context, private val lifes: MutableList<LifeItem>) : RecyclerBaseAdapter<RecyclerView.ViewHolder>(context) {

    override fun getItemCount(): Int = lifes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = inflater.inflate(R.layout.item_life, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemHolder).bind(lifes[position])
    }

    class ItemHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: LifeItem) {
            iv_pic.setImageResource(item.pic_id)
            tv_title.text = item.title
        }
    }

}
