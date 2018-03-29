package com.example.grammar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_variable.*

class VariableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_variable)

        val origin:Float = 65.0f
        tv_origin.text = origin.toString()
        var int:Int //声明整型变量
        btn_int.setOnClickListener { int=origin.toInt(); tv_convert.text=int.toString() }
        var long:Long //声明长整型变量
        btn_long.setOnClickListener { long=origin.toLong(); tv_convert.text=long.toString() }
        var float:Float //声明浮点型变量
        btn_float.setOnClickListener { float=origin.toDouble().toFloat(); tv_convert.text=float.toString() }
        var double:Double //声明双精度型变量
        btn_double.setOnClickListener { double=origin.toDouble(); tv_convert.text=double.toString() }
        var boolean:Boolean //声明布尔型变量
        btn_boolean.setOnClickListener { boolean=origin.isNaN(); tv_convert.text=boolean.toString() }
        var char:Char //声明字符型变量
        btn_char.setOnClickListener { char=origin.toChar(); tv_convert.text=char.toString() }
        //只有Float和Double可调用isNaN方法，其它基本类型都没有isNaN
    }
}
