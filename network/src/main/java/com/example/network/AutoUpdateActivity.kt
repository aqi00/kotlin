package com.example.network

import com.example.network.adapter.RecyclerGridAdapter
import com.example.network.bean.RecyclerInfo
import com.example.network.bean.VersionCheck
import com.example.network.util.alert
import com.example.network.util.downloader
import com.example.network.util.highlight
import com.example.network.widget.SpacesItemDecoration

import android.app.DownloadManager
import android.app.ProgressDialog
import android.app.DownloadManager.Query
import android.app.DownloadManager.Request
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.text.style.ForegroundColorSpan
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_auto_update.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

/**
 * Created by ouyangshen on 2017/9/24.
 */
class AutoUpdateActivity : AppCompatActivity() {
    private val checkUrl = "http://192.168.0.212:8080/HttpTest/checkUpdate";
    //private val checkUrl = "http://192.168.1.5:8080/HttpTest/checkUpdate"
    private val handler = Handler()
    private var appVc: VersionCheck = VersionCheck()
    private var downloadId: Long = 0
    private lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_update)
        tl_head.title = "商城首页"
        setSupportActionBar(tl_head)
        tl_head.setNavigationOnClickListener { finish() }

        rv_grid.layoutManager = GridLayoutManager(this, 5)
        rv_grid.adapter = RecyclerGridAdapter(this, RecyclerInfo.defaultGrid)
        rv_grid.itemAnimator = DefaultItemAnimator()
        rv_grid.addItemDecoration(SpacesItemDecoration(1))

        btn_no_request.setOnClickListener {
            //测试使用爱奇艺的安装包，包名是com.qiyi.video
            val result = "{\"app_name\":\"爱奇艺\",\"package_name\":\"com.qiyi.video\",\"version_code\":80930,\"version_name\":\"8.8.5\",\"need_update\":true,\"download_url\":\"http://www.lenovomm.com/appdown/21404091-2\"}"
            checkUpdate(result)
        }
        btn_need_request.setOnClickListener {
            val pi = packageManager.getPackageInfo(packageName, 0)
            //开启分线程执行后端接口调用
            doAsync {
                //从服务端获取版本升级信息
                val url = "$checkUrl?package_name=${pi.packageName}&version_name=${pi.versionName}"
                val result = URL(url).readText()
                //回到主线程在界面上弹窗提示待升级的版本
                uiThread { checkUpdate(result) }
            }
        }
    }

    private fun checkUpdate(result: String) {
        //利用gson库将json字符串自动转换为对应格式的数据类实例
        val vc = Gson().fromJson(result, VersionCheck::class.java)
        //检查本地是否存在相同版本的安装包
        vc.local_path = getLocalPath(vc)
        val message = "系统检测到${vc.app_name}的最新版本号是${vc.version_name}，" +
                if (vc.local_path.isNotEmpty()) "本次升级无需流量。"
                else "快去在线升级吧。"
        //高亮显示文本信息中的最新版本号
        val spanText = message.highlight(vc.version_name, ForegroundColorSpan(Color.RED))
        //弹出提示升级的对话框
        alert(spanText, "升级提醒") {
            positiveButton("确定升级") { startInstallApp(vc) }
            negativeButton("以后再说") {  }
        }.show()
    }

    private fun getLocalPath(vc: VersionCheck): String {
        var local_path = ""
        //遍历本地所有的apk文件
        val cursor = contentResolver.query(MediaStore.Files.getContentUri("external"),
                null, "mime_type=\"application/vnd.android.package-archive\"", null, null)
        while (cursor.moveToNext()) {
            //TITLE获取文件名，DATA获取文件完整路径，SIZE获取文件大小
            val path = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA))
            //从apk文件中解析得到该安装包的程序信息
            val pi = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES)
            if (pi != null) {
                //找到包名与版本号都符合条件的apk文件
                if (vc.package_name==pi.packageName && vc.version_name==pi.versionName) {
                    local_path = path
                }
            }
        }
        cursor.close()
        return local_path
    }

    //开始执行升级处理。如果本地已有安装包，则直接进行操作；如果不存在，则从网络下载安装包。
    private fun startInstallApp(vc: VersionCheck) {
        appVc = vc
        //本地路径非空，表示存储卡找到最新版本的安装包，此时无需下载即可进行安装操作
        if (vc.local_path.isNotEmpty()) {
            handler.postDelayed(mInstall, 100)
        } else {
            //构建安装包下载地址的请求任务
            val down = Request(Uri.parse(vc.download_url))
            down.setAllowedNetworkTypes(Request.NETWORK_MOBILE or Request.NETWORK_WIFI)
            //隐藏通知栏上的下载消息
            down.setNotificationVisibility(Request.VISIBILITY_HIDDEN)
            down.setVisibleInDownloadsUi(false)
            //指定下载文件的保存路径
            down.setDestinationInExternalFilesDir(this,
                    Environment.DIRECTORY_DOWNLOADS, "${vc.package_name}.apk")
            //将请求任务添加到下载队列中
            downloadId = downloader.enqueue(down)
            handler.postDelayed(mRefresh, 100)
            //弹出进度对话框，用于展示下载进度
            val message = "正在下载${appVc.app_name}的安装包"
            dialog = progressDialog(message, "请稍候")
            dialog.show()
        }
    }

    private val mRefresh = object : Runnable {
        override fun run() {
            var bFinish = false
            //构建下载任务的查询对象
            val down_query = Query()
            down_query.setFilterById(downloadId)
            //获得下载管理器的查询游标，轮询下载任务的下载进度
            val cursor = downloader.query(down_query)
            while (cursor.moveToNext()) {
                val uriIdx = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)
                val totalSizeIdx = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES)
                val nowSizeIdx = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)
                //计算当前的下载进度
                val progress = (100 * cursor.getLong(nowSizeIdx) / cursor.getLong(totalSizeIdx)).toInt()
                if (cursor.getString(uriIdx) == null) {
                    break
                }
                //实时刷新进度对话框上的下载进度
                dialog.progress = progress
                if (progress >= 100) {
                    bFinish = true
                }
            }
            cursor.close()
            if (!bFinish) {
                handler.postDelayed(this, 100)
            } else {
                //下载完毕，进行后续的安装操作
                dialog.dismiss()
                toast("${appVc.app_name}的安装包下载完成")
                handler.postDelayed(mInstall, 100)
            }
        }
    }

    private val mInstall = Runnable {
        //此处省略具体的APK安装过程。。。
        val message = "正在安装${appVc.app_name}的最新版本"
        dialog = progressDialog(message, "请稍候")
        dialog.show()
        handler.postDelayed({
            if (dialog.isShowing) {
                dialog.dismiss()
                //弹出升级完毕的提醒对话框
                val message = "${appVc.app_name}的${appVc.version_name}版本升级完成。"
                val spanText = message.highlight(appVc.version_name, ForegroundColorSpan(Color.RED))
                alert(spanText, "升级完毕") {
                    positiveButton("我知道了") {  }
                }.show()
            }
        }, 5000) //延迟5秒，模拟安装升级包花费了5秒时间
    }

}
