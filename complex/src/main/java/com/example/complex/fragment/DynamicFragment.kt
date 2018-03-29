package com.example.complex.fragment

import com.example.complex.R

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class DynamicFragment : Fragment() {
    private var ctx: Context? = null
    private var mPosition: Int = 0
    private var mImageId: Int = 0
    private var mDesc: String? = null
    private var mPrice: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        ctx = activity
        //碎片内部通过arguments获取外部的输入参数
        if (arguments != null) {
            mPosition = arguments!!.getInt("position", 0)
            mImageId = arguments!!.getInt("image_id", 0)
            mDesc = arguments!!.getString("desc")
            mPrice = arguments!!.getInt("price")
        }
        val view = inflater.inflate(R.layout.fragment_dynamic, container, false)
        //注意Fragment内部仍需通过findViewById获得控件对象
        val iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
        val tv_desc = view.findViewById<TextView>(R.id.tv_desc)
        iv_pic.setImageResource(mImageId)
        tv_desc.text = "$mDesc\n售价：$mPrice"
        return view
    }

    companion object {
        //利用伴生对象定义获取碎片实例的静态方法
        fun newInstance(position: Int, image_id: Int, desc: String, price: Int): DynamicFragment {
            val fragment = DynamicFragment()
            val bundle = Bundle()
            bundle.putInt("position", position)
            bundle.putInt("image_id", image_id)
            bundle.putString("desc", desc)
            bundle.putInt("price", price)
            //外部通过arguments向碎片传递输入参数
            fragment.arguments = bundle
            return fragment
        }
    }

}
