package com.example.storage

import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.view.View

import com.example.storage.util.DateUtil
import com.example.storage.util.FileUtil

import kotlinx.android.synthetic.main.activity_image_write.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/9/10.
 */
class ImageWriteActivity : AppCompatActivity() {
    private val types = listOf("未婚", "已婚")
    private var bMarried = false
    private var mPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_write)
        mPath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/"

        sp_married.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = types[0]
        tv_spinner.setOnClickListener {
            selector("请选择婚姻状况", types) { i ->
                tv_spinner.text = types[i]
                bMarried = if (i == 0) false else true
            }
        }

        btn_save.setOnClickListener {
            when {
                et_name.text.isEmpty() -> toast("请先填写姓名")
                et_age.text.isEmpty() -> toast("请先填写年龄")
                et_height.text.isEmpty() -> toast("请先填写身高")
                et_weight.text.isEmpty() -> toast("请先填写体重")
                else -> {
                    val bitmap = ll_info.drawingCache
                    val file_path = "$mPath${DateUtil.getFormatTime()}.png"
                    FileUtil.saveImage(file_path, bitmap)
                    tv_path.text = "用户注册信息图片的保存路径为：\n$file_path"
                    toast("图片已存入临时目录")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        ll_info.isDrawingCacheEnabled = true
    }

    override fun onStop() {
        super.onStop()
        ll_info.isDrawingCacheEnabled = false
    }

}
