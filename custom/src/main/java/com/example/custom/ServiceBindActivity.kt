package com.example.custom

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView

import com.example.custom.service.BindService
import com.example.custom.util.DateUtil

import kotlinx.android.synthetic.main.activity_service_bind.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/9/17.
 */
class ServiceBindActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_bind)
        Bind.tv_bind = findViewById<TextView>(R.id.tv_bind)
        btn_start_bind.setOnClickListener {
            val intentBind = intentFor<BindService>("request_content" to et_request.text.toString())
            val bindFlag = bindService(intentBind, mFirstConn, Context.BIND_AUTO_CREATE)
            Log.d(TAG, "bindFlag=" + bindFlag)
            toast("服务已绑定启动")
        }
        btn_unbind.setOnClickListener {
            if (mBindService != null) {
                unbindService(mFirstConn)
                mBindService = null
                toast("服务已解除绑定")
            }
        }
    }

    private var mBindService: BindService? = null
    private val mFirstConn = object : ServiceConnection {
        //获取服务对象时的操作
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            //如果服务运行于另外一个进程，则不能直接强制转换类型，
            //否则会报错“java.lang.ClassCastException: android.os.BinderProxy cannot be cast to...”
            mBindService = (service as BindService.LocalBinder).service
            Log.d(TAG, "onServiceConnected")
        }

        //无法获取到服务对象时的操作
        override fun onServiceDisconnected(name: ComponentName) {
            mBindService = null
            Log.d(TAG, "onServiceDisconnected")
        }

    }

    companion object Bind {
        private val TAG = "ServiceBindActivity"
        private var tv_bind: TextView? = null
        private var mDesc = ""

        fun showText(desc: String) {
            mDesc = "$mDesc${DateUtil.nowTime} $desc\n"
            tv_bind?.text = mDesc
        }
    }

}
