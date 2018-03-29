package com.example.custom

import com.example.custom.adapter.PlanetAdapter
import com.example.custom.bean.Planet

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_measure_view.*

/**
 * Created by ouyangshen on 2017/9/17.
 */
class MeasureViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measure_view)
        //lv_planet是系统自带的ListView，被ScrollView嵌套只能显示一行
        val adapter1 = PlanetAdapter(this, Planet.defaultList)
        lv_planet.adapter = adapter1
        lv_planet.onItemClickListener = adapter1
        lv_planet.onItemLongClickListener = adapter1
        //nslv_planet是自定义控件NoScrollListView，会显示所有行
        val adapter2 = PlanetAdapter(this, Planet.defaultList)
        nslv_planet.adapter = adapter2
        nslv_planet.onItemClickListener = adapter2
        nslv_planet.onItemLongClickListener = adapter2
    }
}
