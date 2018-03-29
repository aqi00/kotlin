package com.example.custom

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.activity_text_progress.*
import org.jetbrains.anko.selector

/**
 * Created by ouyangshen on 2017/9/17.
 */
class TextProgressActivity : AppCompatActivity() {
    private val progresses = listOf("0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_progress)

        sp_progress.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = progresses[0]
        tv_spinner.setOnClickListener {
            selector("请选择进度值", progresses) { i ->
                tv_spinner.text = progresses[i]
                val progress = progresses[i].toInt()
                tpb_progress.progress = progress
                tpb_progress.progressText = "当前处理进度为$progress%"
            }
        }
    }

}
