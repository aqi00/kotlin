package com.example.complex

import com.example.complex.adapter.RecyclerLinearAdapter
import com.example.complex.bean.RecyclerInfo
import com.example.complex.widget.SpacesItemDecoration

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_recycler_linear.*

/**
 * Created by ouyangshen on 2017/9/3.
 */
class RecyclerLinearActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_linear)

        rv_linear.layoutManager = LinearLayoutManager(this)
        val adapter = RecyclerLinearAdapter(this, RecyclerInfo.defaultList)
        adapter.setOnItemClickListener(adapter)
        adapter.setOnItemLongClickListener(adapter)
        rv_linear.adapter = adapter
        rv_linear.itemAnimator = DefaultItemAnimator()
        rv_linear.addItemDecoration(SpacesItemDecoration(1))
    }

}
