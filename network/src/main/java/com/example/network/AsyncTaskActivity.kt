package com.example.network

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.activity_async_task.*
import org.jetbrains.anko.*
import java.util.concurrent.Future

/**
 * Created by ouyangshen on 2017/9/24.
 */
class AsyncTaskActivity : AppCompatActivity() {
    private lateinit var dialog: ProgressDialog
    private val books = listOf("三国演义", "西游记", "红楼梦")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task)

        sp_style.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = books[0]
        tv_spinner.setOnClickListener {
            selector("请选择要加载的小说", books) { i ->
                tv_spinner.text = books[i]
                when (i) {
                    0 -> { dialogCircle(books[i]) }
                    1 -> { dialogBar(books[i]) }
                    else -> { progressBar(books[i]) }
                }
            }
        }
    }

    //展示在圆圈进度对话框
    private fun dialogCircle(book: String) {
        dialog = indeterminateProgressDialog("${book}页面加载中……", "稍等")
        doAsync {
            // 睡眠200毫秒模拟网络通信处理
            for (ratio in 0..20) Thread.sleep(200)
            //处理完成，回到主线程在界面上显示书籍加载结果
            uiThread { finishLoad(book) }
        }
    }

    //展示在长条进度对话框
    private fun dialogBar(book: String) {
        dialog = progressDialog("${book}页面加载中……", "稍等")
        doAsync {
            for (ratio in 0..20) {
                Thread.sleep(200)
                //处理过程中，实时通知主线程当前的处理进度
                uiThread { dialog.progress = ratio*100/20 }
            }
            uiThread { finishLoad(book) }
        }
    }

    //展示在进度条ProgressBar
    private fun progressBar(book: String) {
        //构造异步处理需要执行的代码段longTask，返回字符串类型
        val longTask: (AnkoAsyncContext<Context>.() -> String) = {
            for (ratio in 0..20) Thread.sleep(200)
            "加载好了" //这是longTask处理完成的返回结果
        }
        //doAsyncResult返回一个异步线程对象
        val future : Future<String> = doAsyncResult(null, longTask)
        for (count in 0..10) {
            if (future.isDone) {
                //isDone是否完成，isCancelled是否取消，get获取处理结果
                tv_async.text = "您要阅读的《${book}》已经${future.get()}"
                pb_async.progress = 100
                break
            }
            pb_async.progress = count*100/10
            Thread.sleep(1000)
        }
    }

     private fun finishLoad(book: String) {
        tv_async.text = "您要阅读的《$book》已经加载完毕"
        //如果进度对话框还在显示，则关闭进度对话框
        if (dialog.isShowing) dialog.dismiss()
    }

}
