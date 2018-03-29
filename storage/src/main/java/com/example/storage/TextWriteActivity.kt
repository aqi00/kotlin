package com.example.storage

import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.view.View

import com.example.storage.util.DateUtil

import kotlinx.android.synthetic.main.activity_text_write.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast
import java.io.File

/**
 * Created by ouyangshen on 2017/9/10.
 */
class TextWriteActivity : AppCompatActivity() {
    private val types = listOf("未婚", "已婚")
    private var bMarried = false
    private var mPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_write)
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
                    val content = "　姓名：${et_name.text}\n" +
                            "　年龄：${et_age.text}\n" +
                            "　身高：${et_height.text}\n" +
                            "　体重：${et_weight.text}\n" +
                            "　婚否：${types[if (!bMarried) 0 else 1]}\n" +
                            "　注册时间：${DateUtil.nowDateTime}\n"
                    val file_path = "$mPath${DateUtil.getFormatTime()}.txt"
                    //writeText写入文本，writeBytes写入字节数组
                    //appendText追加文本，appendBytes追加字节数组
                    File(file_path).writeText(content)
                    tv_path.text = "用户注册信息文件的保存路径为：\n$file_path"
                    toast("文本已写入临时目录")
                }
            }
        }
    }

}
