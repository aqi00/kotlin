package com.example.network

import com.example.network.bean.Contact
import com.example.network.util.CommunicationUtil
import com.example.network.util.ViewUtil

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_content_resolver.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/9/24.
 */
class ContentResolverActivity : AppCompatActivity() {
    private var contactCount = ""
    private var contactResult = ""
    private var dialog: ProgressDialog? = null
    private val ADD = 0
    private val QUERY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_resolver)
        btn_add_contact.setOnClickListener {
            ViewUtil.hideAllInputMethod(this)
            dialog = indeterminateProgressDialog("正在写入联系人信息", "请稍候")
            dialog?.show()
            Thread { addContactInfo() }.start()
        }
        tv_read_contact.setOnClickListener {
            alert(contactResult, contactCount) {
                positiveButton("确定") {}
            }.show()
        }
        showContactInfo()
    }

    private fun showContactInfo() {
        dialog = indeterminateProgressDialog("正在读取联系人信息", "请稍候")
        dialog?.show()
        //查询联系人操作可能较耗时，故需放在线程中处理
        Thread { queryContactInfo() }.start()
    }

    private fun addContactInfo() {
        val contact = Contact()
        contact.name = et_contact_name.text.toString()
        contact.phone = et_contact_phone.text.toString()
        contact.email = et_contact_email.text.toString()
        //方式一，使用ContentResolver多次写入，每次一个字段
        CommunicationUtil.addContacts(contentResolver, contact)
        //方式二，使用ContentProviderOperation一次写入，每次多个字段
        //CommunicationUtil.addFullContacts(getContentResolver(), contact);
        handler.sendEmptyMessage(ADD)
    }

    private fun queryContactInfo() {
        contactResult = CommunicationUtil.readAllContacts(contentResolver)
        val list = contactResult.split("\n")
        //val count = userResult.count({ it == '\n' })
        contactCount = "当前共找到${list.size-1}位联系人"
        handler.sendEmptyMessage(QUERY)
    }

    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            dialog?.dismiss()
            when (msg.what) {
                ADD -> {
                    toast("成功写入联系人信息")
                    showContactInfo()
                }
                else -> tv_read_contact.text = contactCount
            }
        }
    }

}
