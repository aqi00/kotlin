package com.example.network

import android.app.PendingIntent
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.database.ContentObserver
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.telephony.SmsManager
import android.util.Log
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_content_observer.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog

/**
 * Created by ouyangshen on 2017/9/24.
 */
class ContentObserverActivity : AppCompatActivity() {
    private var mObserver: SmsGetObserver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_observer)
        Observer.tv_check_flow = findViewById<TextView>(R.id.tv_check_flow)
        tv_check_flow.setOnClickListener {
            alert(mCheckResult, "收到流量校准短信") {
                positiveButton("确定") {}
            }.show()
        }
        btn_check_flow.setOnClickListener {
            var dialog = indeterminateProgressDialog("正在进行流量校准", "请稍候")
            dialog.show()
            //查询数据流量，移动号码的查询方式为发送短信内容“18”给“10086”
            //电信和联通号码的短信查询方式请咨询当地运营商客服热线
            sendSmsAuto("10086", "18")
            Handler().postDelayed({
                if (dialog.isShowing == true) {
                    dialog.dismiss()
                }}, 5000)
        }
        mSmsUri = Uri.parse("content://sms")
        mSmsColumn = arrayOf("address", "body", "date")
        mObserver = SmsGetObserver(this, Handler())
        //注册短信接收的内容观察器
        contentResolver.registerContentObserver(mSmsUri!!, true, mObserver!!)
    }

    override fun onDestroy() {
        //注销短信接收的内容观察器
        contentResolver.unregisterContentObserver(mObserver!!)
        super.onDestroy()
    }

    //短信发送广播，如需处理可注册该事件的BroadcastReceiver
    private val SENT_SMS_ACTION = "com.example.network.SENT_SMS_ACTION"
    //短信接收广播，如需处理可注册该事件的BroadcastReceiver
    private val DELIVERED_SMS_ACTION = "com.example.network.DELIVERED_SMS_ACTION"

    fun sendSmsAuto(phoneNumber: String, message: String) {
        //声明短信发送的广播意图
        val sentIntent = Intent(SENT_SMS_ACTION)
        sentIntent.putExtra("phone", phoneNumber)
        sentIntent.putExtra("message", message)
        val sentPI = PendingIntent.getBroadcast(this, 0, sentIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        //声明短信接收的广播意图
        val deliverIntent = Intent(DELIVERED_SMS_ACTION)
        deliverIntent.putExtra("phone", phoneNumber)
        deliverIntent.putExtra("message", message)
        val deliverPI = PendingIntent.getBroadcast(this, 1, deliverIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        //要确保打开发送短信的完全权限，不是那种还需提示的不完整权限
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(phoneNumber, null, message, sentPI, deliverPI)
    }

    //定义一个短信观察器的嵌套类
    private class SmsGetObserver(private val mContext: Context, handler: Handler) : ContentObserver(handler) {

        override fun onChange(selfChange: Boolean) {
            var sender = ""
            var content = ""
            //查询收件箱中来自10086的最近短信
            val selection = "address='10086' and date>${System.currentTimeMillis()-1000*60*60}"
            val cursor = mContext.contentResolver.query(
                    mSmsUri!!, mSmsColumn, selection, null, " date desc")
            while (cursor.moveToNext()) {
                sender = cursor.getString(0)
                content = cursor.getString(1)
                break
            }
            cursor.close()
            mCheckResult = "发送号码：$sender\n短信内容：$content"
            //将解析后的短信内容显示到界面上
            Observer.tv_check_flow!!.text = "流量校准结果如下：\n\t" +
                    "总流量为：${findFlow(content, "总流量为", "MB")}\n\t" +
                    "已使用：${findFlow(content, "已使用", "MB")}\n\t" +
                    "剩余：${findFlow(content, "剩余", "MB")}"
            super.onChange(selfChange)
        }
    }

    companion object Observer {
        private var tv_check_flow: TextView? = null
        private var mCheckResult: String = ""
        private var mSmsUri: Uri? = null
        private var mSmsColumn: Array<String>? = null
        //在伴生对象中定义解析短信内容的方法
        private fun findFlow(sms: String, begin: String, end: String): String {
            val begin_pos = sms.indexOf(begin)
            if (begin_pos < 0) {
                return "未获取"
            }
            val sub_sms = sms.substring(begin_pos)
            val end_pos = sub_sms.indexOf(end)
            return if (end_pos < 0) {
                "未获取"
            } else sub_sms.substring(begin.length, end_pos + end.length)
        }
    }
}
