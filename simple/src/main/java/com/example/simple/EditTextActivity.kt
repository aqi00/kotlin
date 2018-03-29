package com.example.simple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_edit_text.*

/**
 * Created by ouyangshen on 2017/8/27.
 */
class EditTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)
        //注意不能直接给EdiText控件的text属性赋值
        //否则会报错Editable与String类型不匹配
        //只能调用setText方法对EdiText控件设置文本
        et_phone.setText("");
        //显示明文数字
        et_phone.inputType = InputType.TYPE_CLASS_NUMBER
        //显示明文密码
        //et_phone.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        //隐藏密码
        //et_phone.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        //给编辑框添加文本变化的监听器
        et_phone.addTextChangedListener(EditWatcher())
    }

    private inner class EditWatcher : TextWatcher {

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable) {
            var str = s.toString()
            //发现输入回车符或换行符
            if (str.indexOf("\r") >= 0 || str.indexOf("\n") >= 0) {
                //去掉回车符和换行符
                str = str.replace("\r", "").replace("\n", "")
            }
            if (str.length >= 11) {
                tv_phone.text = "您输入的手机号码是：$str"
            }
        }
    }
}
