package com.example.storage

import com.example.storage.database.UserDBHelper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_sqlite_read.*

/**
 * Created by ouyangshen on 2017/9/10.
 */
class SQLiteReadActivity : AppCompatActivity() {
    private lateinit var mHelper: UserDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_read)
        mHelper = UserDBHelper.getInstance(this)
        readSQLite()
        btn_delete.setOnClickListener {
            mHelper.deleteAll()
            //重新读取数据库
            readSQLite()
        }
    }

    private fun readSQLite() {
        val userArray = mHelper.queryAll()
        var desc = "数据库查询到${userArray.size}条记录，详情如下："
        for (i in userArray.indices) {
            val item = userArray[i]
            desc = "$desc\n第${i+1}条记录信息如下：" +
                    "\n　姓名为${item.name}" +
                    "\n　年龄为${item.age}" +
                    "\n　身高为${item.height}" +
                    "\n　体重为${item.weight}" +
                    "\n　婚否为${item.married}" +
                    "\n　更新时间为${item.update_time}"
        }
        if (userArray.isEmpty()) {
            desc = "数据库查询到的记录为空"
        }
        tv_sqlite.text = desc
    }

}
