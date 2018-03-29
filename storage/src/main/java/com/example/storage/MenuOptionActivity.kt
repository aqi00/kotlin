package com.example.storage

import com.example.storage.util.DateUtil

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_menu_option.*

/**
 * Created by ouyangshen on 2017/9/10.
 */
class MenuOptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_option)
        setSupportActionBar(tl_head)
        tl_head.setNavigationOnClickListener { finish() }

        //注意：如果当前页面继承自AppCompatActivity，并且appcompat版本不低于22.1.0
        //那么调用openOptionsMenu方法将不会弹出菜单。这应该是Android的一个bug
        btn_option.setOnClickListener { openOptionsMenu() }
        setRandomTime()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_change_time -> setRandomTime()
            R.id.menu_change_color -> tv_option.setTextColor(randomColor)
            R.id.menu_change_bg -> tv_option.setBackgroundColor(randomColor)
        }
        return true
    }

    private fun setRandomTime() {
        val desc = "${DateUtil.nowDateTime} 这里是菜单显示文本"
        tv_option.text = desc
    }

    private val mColorArray = intArrayOf(Color.BLACK, Color.WHITE, Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.GRAY, Color.DKGRAY)
    private val randomColor: Int
        get() {
            val random = (Math.random() * 10 % 10).toInt()
            return mColorArray[random]
        }

}
