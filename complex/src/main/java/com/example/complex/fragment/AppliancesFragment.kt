package com.example.complex.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.example.complex.R
import com.example.complex.adapter.ChannelPagerAdapter
import com.example.complex.adapter.RecyclerStaggeredAdapter
import com.example.complex.bean.RecyclerInfo
import com.example.complex.widget.SpacesItemDecoration

class AppliancesFragment : Fragment(), OnRefreshListener {
    private var ctx: Context? = null
    lateinit var srl_appliances: SwipeRefreshLayout
    lateinit var rv_appliances: RecyclerView
    lateinit var adapter: RecyclerStaggeredAdapter
    private var alls = RecyclerInfo.defaultAppi

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        ctx = activity
        val view = inflater.inflate(R.layout.fragment_appliances, container, false)

        srl_appliances = view.findViewById<SwipeRefreshLayout>(R.id.srl_appliances)
        srl_appliances.setOnRefreshListener(this)
        srl_appliances.setColorSchemeResources(
                R.color.red, R.color.orange, R.color.green, R.color.blue)

        rv_appliances = view.findViewById<RecyclerView>(R.id.rv_appliances)
        val manager = StaggeredGridLayoutManager(3, LinearLayout.VERTICAL)
        rv_appliances.layoutManager = manager
        adapter = RecyclerStaggeredAdapter(ctx!!, alls)
        adapter.setOnItemClickListener(adapter)
        adapter.setOnItemLongClickListener(adapter)
        rv_appliances.adapter = adapter
        rv_appliances.itemAnimator = DefaultItemAnimator()
        rv_appliances.addItemDecoration(SpacesItemDecoration(3))

        return view
    }

    override fun onRefresh() {
        mHandler.postDelayed(mRefresh, 2000)
    }

    private val mHandler = Handler()
    private val mRefresh = Runnable {
        srl_appliances.isRefreshing = false
        val i = alls.size - 1
        var count = 0
        while (count < 5) {
            val item = alls[i]
            alls.removeAt(i)
            alls.add(0, item)
            count++
        }
        adapter.notifyDataSetChanged()
        rv_appliances.scrollToPosition(0)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        //如果该页是一打开的默认页，则setUserVisibleHint先于onCreateView执行，此时ctx为空
        if (ctx != null) {
            val intent = Intent(ChannelPagerAdapter.EVENT)
            intent.putExtra("color", ctx!!.resources.getColor(R.color.indigo))
            ctx!!.sendBroadcast(intent)
        }
    }

}
