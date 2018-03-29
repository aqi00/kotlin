package com.example.complex.adapter

import com.example.complex.R

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup

import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_collapse.*

class RecyclerCollapseAdapter(context: Context, private val titles: Array<String>) : RecyclerBaseAdapter<ViewHolder>(context) {

    override fun getItemCount(): Int = titles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.item_collapse, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ItemHolder).bind(titles[position])
    }

    class ItemHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: String) {
            tv_seq.text = "${position+1}"
            tv_title.text = item
        }
    }

}
