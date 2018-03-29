package com.example.network

import com.example.network.util.DateUtil
import com.example.network.util.locator
import com.example.network.util.criteria

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_http_request.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL
import java.text.MessageFormat

/**
 * Created by ouyangshen on 2017/9/24.
 */
class HttpRequestActivity : AppCompatActivity() {
    private var mLocation = ""
    private val handler = Handler()
    private var bLocationEnable = false
    private val mapsUrl = "http://maps.google.cn/maps/api/geocode/json?latlng={0},{1}&sensor=true&language=zh-CN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_request)
        initLocation()
        handler.postDelayed(mRefresh, 100)
    }

    private fun initLocation() {
        val bestProvider: String = locator.getBestProvider(criteria, true)
        if (bestProvider.isNotEmpty() && locator.isProviderEnabled(bestProvider)) {
            tv_location.text = "正在获取${bestProvider}定位对象"
            mLocation = "定位类型=$bestProvider"
            beginLocation(bestProvider)
            bLocationEnable = true
        } else {
            tv_location.text = "\n${bestProvider}定位不可用"
            bLocationEnable = false
        }
    }

    //在主线程中把定位信息连同地址信息都打印到界面上
    private fun findAddress(location: Location, address: String) {
        tv_location.text = "$mLocation\n定位对象信息如下： " +
                "\n\t时间：${DateUtil.nowDateTime}" +
                "\n\t经度：${location.longitude}，纬度：${location.latitude}" +
                "\n\t高度：${location.altitude}米，精度：${location.accuracy}米" +
                "\n\t地址：$address"
    }

    //位置监听器侦听到定位变化事件，就调用该函数请求详细地址
    private fun setLocationText(location: Location?) {
        if (location != null) {
            doAsync {
                //根据经纬度数据从谷歌地图获取详细地址信息
                val url = MessageFormat.format(mapsUrl, location.latitude, location.longitude)
                val text = URL(url).readText()
                val obj = JSONObject(text)
                val resultArray = obj.getJSONArray("results")
                var address = ""
                //解析json字符串，其中formatted_address字段为具体地址名称
                if (resultArray.length() > 0) {
                    val resultObj = resultArray.getJSONObject(0)
                    address = resultObj.getString("formatted_address")
                }
                //获得该地点的详细地址之后，回到主线程把地址显示在界面上
                uiThread { findAddress(location, address) }
            }
        } else {
            tv_location.text = "$mLocation\n暂未获取到定位对象"
        }
    }

    private fun beginLocation(method: String) {
        locator.requestLocationUpdates(method, 300, 0f, mLocationListener)
        val location = locator.getLastKnownLocation(method)
        setLocationText(location)
    }

    // 位置监听器
    private val mLocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            setLocationText(location)
        }

        override fun onProviderDisabled(arg0: String) {}

        override fun onProviderEnabled(arg0: String) {}

        override fun onStatusChanged(arg0: String, arg1: Int, arg2: Bundle) {}
    }

    private val mRefresh = object : Runnable {
        override fun run() {
            if (!bLocationEnable) {
                initLocation()
                handler.postDelayed(this, 1000)
            }
        }
    }

    override fun onDestroy() {
        locator.removeUpdates(mLocationListener)
        super.onDestroy()
    }

}
