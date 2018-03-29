package com.example.network

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.activity_text_circle.*
import org.jetbrains.anko.selector

/**
 * Created by ouyangshen on 2017/9/24.
 */
class ProgressCircleActivity : AppCompatActivity() {
    private val progresses = listOf("0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_circle)

        sp_progress.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = progresses[0]
        tv_spinner.setOnClickListener {
            selector("请选择进度值", progresses) { i ->
                tv_spinner.text = progresses[i]
                tpc_progress.setProgress(progresses[i].toInt(), 50f)
            }
        }
    }

}
