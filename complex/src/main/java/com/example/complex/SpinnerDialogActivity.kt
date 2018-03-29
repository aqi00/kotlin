package com.example.complex

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.activity_spinner_dialog.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/9/3.
 */
class SpinnerDialogActivity : AppCompatActivity() {
    private val satellites = listOf("水星", "金星", "地球", "火星", "木星", "土星")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner_dialog)

        sp_dialog.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = satellites[0]
        tv_spinner.setOnClickListener {
            selector("请选择行星", satellites) { i ->
                tv_spinner.text = satellites[i]
                toast("你选择的行星是${tv_spinner.text}")
            }
        }
    }

}
