package com.example.network

import com.example.network.util.downloader
import com.example.network.util.DateUtil

import android.app.DownloadManager
import android.app.DownloadManager.Request
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_download_apk.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/9/24.
 */
class DownloadApkActivity : AppCompatActivity() {
    private val apkNames = listOf(
            "支付宝", "微信", "手机QQ", "手机淘宝", "爱奇艺", 
            "酷狗音乐", "UC浏览器", "搜狗输入法", "微博", "手机京东")
    private val apkUrls = listOf(
            "https://3g.lenovomm.com/w3g/yydownload/com.eg.android.AlipayGphone/60020",
            "https://3g.lenovomm.com/w3g/yydownload/com.tencent.mm/60020",
            "https://3g.lenovomm.com/w3g/yydownload/com.tencent.mobileqq/60020",
            "https://3g.lenovomm.com/w3g/yydownload/com.taobao.taobao/60020",
            "https://3g.lenovomm.com/w3g/yydownload/com.qiyi.video/60020",
            "https://3g.lenovomm.com/w3g/yydownload/com.kugou.android/60020",
            "https://3g.lenovomm.com/w3g/yydownload/com.UCMobile/60020",
            "https://3g.lenovomm.com/w3g/yydownload/com.sohu.inputmethod.sogou/60020",
            "https://3g.lenovomm.com/w3g/yydownload/com.sina.weibo/60020",
            "https://3g.lenovomm.com/w3g/yydownload/com.jingdong.app.mall/60020")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_apk)
        Download.tv_apk_result = findViewById<TextView>(R.id.tv_apk_result)

        sp_apk_url.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = apkNames[0]
        tv_spinner.setOnClickListener {
            selector("请选择要下载的安装包", apkNames) { i ->
                tv_spinner.text = apkNames[i]
                toast("${apkNames[i]}正在下载，详情见通知栏")
                //声明下载任务的请求对象
                val down = Request(Uri.parse(apkUrls[i]))
                //指定通知栏上的标题文本
                down.setTitle("${apkNames[i]}下载信息")
                //指定通知栏上的描述文本
                down.setDescription("${apkNames[i]}安装包正在下载")
                //手机连上移动网络或者连上WIFI时均可进行下载操作
                down.setAllowedNetworkTypes(Request.NETWORK_MOBILE or Request.NETWORK_WIFI)
                //指定下载通知栏在下载中与完成后都可见
                down.setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                //指定显示在系统的下载页面上
                down.setVisibleInDownloadsUi(true)
                //指定下载文件在本地的保存路径
                down.setDestinationInExternalFilesDir(this,
                        Environment.DIRECTORY_DOWNLOADS, "$i.apk")
                //把下载请求添加到下载队列中
                //这里利用扩展属性实现了自动获取下载管理器实例
                //有关扩展属性的介绍参见第9章的“9.5.2 开始热身：震动器Vibrator”
                downloadId = downloader.enqueue(down)
            }
        }
    }

    // 接收下载完成事件
    class DownloadCompleteReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE && Download.tv_apk_result != null) {
                //获取下载任务的编号
                val downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                Log.d(TAG, " download complete! id : $downId, downloadId=$downloadId")
                Download.tv_apk_result?.visibility = View.VISIBLE
                Download.tv_apk_result?.text = "${DateUtil.getFormatTime()} 编号${downId}的下载任务已完成"
            }
        }
    }

    // 接收下载通知栏的点击事件，在下载过程中有效，下载完成后失效
    class NotificationClickReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.d(TAG, " NotificationClickReceiver onReceive")
            if (intent.action == DownloadManager.ACTION_NOTIFICATION_CLICKED && Download.tv_apk_result != null) {
                //获取下载任务的编号数组
                val downIds = intent.getLongArrayExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS)
                for (downId in downIds) {
                    Log.d(TAG, " notify click! id : $downId, downloadId=$downloadId")
                    //只处理当前下载任务的点击事件
                    if (downId == downloadId) {
                        Download.tv_apk_result?.text = "${DateUtil.getFormatTime()} 编号${downId}的下载进度条被点击了一下"
                    }
                }
            }
        }
    }

    companion object Download {
        private val TAG = "DownloadApkActivity"
        //因为DownloadCompleteReceiver和NotificationClickReceiver是嵌套类
        //嵌套类只能操作类的静态属性，所以把tv_apk_result和downloadId放在伴生对象里面
        var tv_apk_result: TextView? = null
        private var downloadId: Long = 0
    }

}
