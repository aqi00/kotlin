package com.example.complex.adapter

import com.example.complex.R
import com.example.complex.bean.Planet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import org.jetbrains.anko.toast

//ViewHolder中的属性在构造时初始化
class PlanetGridAdapter(private val context: Context, private val planetList: MutableList<Planet>, private val background: Int) : BaseAdapter(), AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    override fun getCount(): Int = planetList.size

    override fun getItem(position: Int): Any = planetList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val holder: ViewHolder
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_grid_view, null)
            holder = ViewHolder(view)
            //视图持有者的内部控件对象已经在构造时一并初始化了，故这里无需再做赋值
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }
        val planet = planetList[position]
        holder.ll_item.setBackgroundColor(background)
        holder.iv_icon.setImageResource(planet.image)
        holder.tv_name.text = planet.name
        holder.tv_desc.text = planet.desc
        return view!!
    }

    inner class ViewHolder(val view: View) {
        //findViewById后面直接跟上“<视图类型>”，即可起到关键字as强制转换类型的功能
        val ll_item: LinearLayout = view.findViewById<LinearLayout>(R.id.ll_item)
        val iv_icon: ImageView = view.findViewById<ImageView>(R.id.iv_icon)
        val tv_name: TextView = view.findViewById<TextView>(R.id.tv_name)
        val tv_desc: TextView = view.findViewById<TextView>(R.id.tv_desc)
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val desc = "您点击了第${position+1}个行星，它的名字是${planetList[position].name}"
        context.toast(desc)
    }

    override fun onItemLongClick(parent: AdapterView<*>, view: View, position: Int, id: Long): Boolean {
        val desc = "您长按了第${position+1}个行星，它的名字是${planetList[position].name}"
        context.toast(desc)
        return true
    }
}
