package com.example.complex.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView

import com.example.complex.R
import org.jetbrains.anko.selector

class BroadcastFragment : Fragment() {
    private var ctx: Context? = null
    lateinit var sp_bg: Spinner
    lateinit var tv_spinner: TextView
    private var mPosition: Int = 0
    private var mImageId: Int = 0
    private var mDesc: String? = null
    private var mSeq = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        ctx = activity
        if (arguments != null) {
            mPosition = arguments!!.getInt("position", 0)
            mImageId = arguments!!.getInt("image_id", 0)
            mDesc = arguments!!.getString("desc")
        }
        val view = inflater!!.inflate(R.layout.fragment_broadcast, container, false)
        val iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
        val tv_desc = view.findViewById<TextView>(R.id.tv_desc)
        sp_bg = view.findViewById<Spinner>(R.id.sp_bg) as Spinner
        tv_spinner = view.findViewById<TextView>(R.id.tv_spinner)
        iv_pic.setImageResource(mImageId)
        tv_desc.text = mDesc
        return view
    }

    private val colorNames = listOf("红色", "黄色", "绿色", "青色", "蓝色")
    private val colorIds = intArrayOf(Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE)

    //初始化选择背景色的下拉框
    private fun initSpinner() {
        sp_bg.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = colorNames[mSeq]
        tv_spinner.setOnClickListener {
            ctx!!.selector("请选择页面背景色", colorNames) { i ->
                tv_spinner.text = colorNames[i]
                mSeq = i
                //设置广播意图的名称为BroadcastFragment.EVENT
                val intent = Intent(BroadcastFragment.EVENT)
                intent.putExtra("seq", i)
                intent.putExtra("color", colorIds[i])
                //已选择新颜色，则发送背景色变更的广播
                ctx!!.sendBroadcast(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        initSpinner()
        bgChangeReceiver = BgChangeReceiver()
        val filter = IntentFilter(BroadcastFragment.EVENT)
        ctx!!.registerReceiver(bgChangeReceiver, filter)
    }

    override fun onStop() {
        ctx!!.unregisterReceiver(bgChangeReceiver)
        super.onStop()
    }

    private var bgChangeReceiver: BgChangeReceiver? = null

    private inner class BgChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if (intent != null) {
                mSeq = intent.getIntExtra("seq", 0)
                tv_spinner.text = colorNames[mSeq]
            }
        }
    }

    companion object {
        //静态属性如果是个常量，则还要添加修饰符const
        const val EVENT = "com.example.complex.fragment.BroadcastFragment"

        fun newInstance(position: Int, image_id: Int, desc: String): BroadcastFragment {
            val fragment = BroadcastFragment()
            val bundle = Bundle()
            bundle.putInt("position", position)
            bundle.putInt("image_id", image_id)
            bundle.putString("desc", desc)
            fragment.arguments = bundle
            return fragment
        }
    }

}
