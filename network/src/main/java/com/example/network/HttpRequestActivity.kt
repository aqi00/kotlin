package com.example.network

import android.Manifest
import android.content.pm.PackageManager
import com.example.network.util.DateUtil
import com.example.network.util.locator
import com.example.network.util.criteria

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_http_request.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
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
    // 谷歌地图从2019年开始必须传入密钥才能根据经纬度获取地址，所以把查询接口改成了国内的天地图
    //private val mapsUrl = "http://maps.google.cn/maps/api/geocode/json?latlng={0},{1}&sensor=true&language=zh-CN"
    private val mapsUrl = "http://api.tianditu.gov.cn/geocoder?postStr={'lon':%f,'lat':%f,'ver':1}&type=geocode&tk=145897399844a50e3de2309513c8df4b"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_request)
    }

    override fun onResume() {
        super.onResume()
        handler.removeCallbacks(mRefresh) // 移除定位刷新任务
        initLocation()
        handler.postDelayed(mRefresh, 100) // 延迟100毫秒启动定位刷新任务
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
                //val url = MessageFormat.format(mapsUrl, location.latitude, location.longitude)
                //根据经纬度数据从天地图获取详细地址信息
                val url = String.format(mapsUrl, location.longitude, location.latitude)
                val text = URL(url).readText()
                val obj = JSONObject(text)
                //解析json字符串，其中formatted_address字段为具体地址名称
                val result = obj.getJSONObject("result")
                var address = result.getString("formatted_address");
                //获得该地点的详细地址之后，回到主线程把地址显示在界面上
                uiThread { findAddress(location, address) }
            }
        } else {
            tv_location.text = "$mLocation\n暂未获取到定位对象"
        }
    }

    private fun beginLocation(method: String) {
        // 检查当前设备是否已经开启了定位功能
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            toast("请授予定位权限并开启定位功能")
            return;
        }
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
