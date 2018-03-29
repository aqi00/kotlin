package com.example.complex

import com.example.complex.adapter.PlanetListAdapter
import com.example.complex.bean.Planet

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.widget.LinearLayout

import kotlinx.android.synthetic.main.activity_list_view.*
import org.jetbrains.anko.selector

/**
 * Created by ouyangshen on 2017/9/3.
 */
class ListViewActivity : AppCompatActivity() {
    private val dividers = listOf(
            "不显示分隔线(分隔线高度为0)",
            "不显示分隔线(分隔线为null)",
            "只显示内部分隔线(先设置分隔线高度)",
            "只显示内部分隔线(后设置分隔线高度)",
            "显示底部分隔线(高度是wrap_content)",
            "显示底部分隔线(高度是match_parent)",
            "显示顶部分隔线(别瞎折腾了，显示不了)",
            "显示全部分隔线(看我用padding大法)")
    private val dividerHeight = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        val adapter = PlanetListAdapter(this, Planet.defaultList, Color.WHITE)
        lv_planet.adapter = adapter
        lv_planet.onItemClickListener = adapter
        lv_planet.onItemLongClickListener = adapter
        val drawable: Drawable = resources.getDrawable(R.drawable.divider_red2)

        sp_list.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = dividers[0]
        tv_spinner.setOnClickListener {
            selector("请选择分隔线显示方式", dividers) { i ->
                tv_spinner.text = dividers[i]
                var params = LinearLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                lv_planet.divider = drawable
                lv_planet.dividerHeight = dividerHeight
                lv_planet.setPadding(0, 0, 0, 0)
                lv_planet.setBackgroundColor(Color.TRANSPARENT)
                when (i) {
                    0 -> lv_planet.dividerHeight = 0
                    1 -> {
                        lv_planet.divider = null
                        lv_planet.dividerHeight = dividerHeight
                    }
                    2 -> {
                        lv_planet.dividerHeight = dividerHeight
                        lv_planet.divider = drawable
                    }
                    3 -> {
                        lv_planet.divider = drawable
                        lv_planet.dividerHeight = dividerHeight
                    }
                    4 -> lv_planet.setFooterDividersEnabled(true)
                    5 -> {
                        params = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1f)
                        lv_planet.setFooterDividersEnabled(true)
                    }
                    6 -> {
                        params = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1f)
                        lv_planet.setFooterDividersEnabled(true)
                        lv_planet.setHeaderDividersEnabled(true)
                    }
                    7 -> {
                        lv_planet.divider = null
                        lv_planet.dividerHeight = dividerHeight
                        lv_planet.setPadding(0, dividerHeight, 0, dividerHeight)
                        lv_planet.background = drawable //API16后才有setBackground方法
                    }
                }
                lv_planet.layoutParams = params
            }
        }
    }

}
