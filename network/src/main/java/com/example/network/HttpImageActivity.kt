package com.example.network

import com.example.network.util.DateUtil

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_http_image.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.File
import java.net.URL

/**
 * Created by ouyangshen on 2017/9/24.
 */
class HttpImageActivity : AppCompatActivity() {
    private val imageUrl = "http://222.77.181.14/ValidateCode.aspx?r="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_image)
        iv_image_code.setOnClickListener { getImageCode() }
        getImageCode()
    }

    //获取网络上的图片验证码
    private fun getImageCode() {
        iv_image_code.isEnabled = false
        doAsync {
            val url = "$imageUrl${DateUtil.getFormatTime()}"
            val bytes = URL(url).readBytes()
            //把字节数组解码为位图数据
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            //也可通过下面三行代码把字节数组写入文件，即生成一个图片文件
            val path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/"
            val file_path = "$path${DateUtil.getFormatTime()}.png"
            File(file_path).writeBytes(bytes)
            //获得验证码图片数据，回到主线程把验证码显示在界面上
            uiThread { finishGet(bitmap) }
        }
    }

    //在主线程中显示获得到的验证码图片
    private fun finishGet(bitmap: Bitmap) {
        iv_image_code.setImageBitmap(bitmap)
        iv_image_code.isEnabled = true
    }

}
