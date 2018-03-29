package com.example.grammar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_string.*

class StringActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_string)

        val origin:String = tv_origin.text.toString()
        var origin_trim:String = origin
        //如果字符串中有小数点
        if (origin_trim.indexOf('.') > 0) {
            //则截取小数点前面的整数部分
            origin_trim = origin_trim.substring(0, origin_trim.indexOf('.'))
        }
        //tv_convert.text = origin_trim

        var int:Int
        btn_int.setOnClickListener { int=origin_trim.toInt(); tv_convert.text=int.toString() }
        var long:Long
        btn_long.setOnClickListener { long=origin_trim.toLong(); tv_convert.text=long.toString() }
        var float:Float
        btn_float.setOnClickListener { float=origin.toFloat(); tv_convert.text=float.toString() }
        var double:Double
        btn_double.setOnClickListener { double=origin.toDouble(); tv_convert.text=double.toString() }
        //origin.toBoolean(); //只能转换字符串“true”和“false”
        var char_array:CharArray
        btn_chararray.setOnClickListener {
            //把字符串转换为字符数组
            char_array = origin.toCharArray()
            var str:String = ""
            for (item in char_array) {
                str = str + item.toString() + ","
            }
            tv_convert.text = str.toString()
        }

        btn_replace.setOnClickListener {
            //把字符串中的小数点替换为加号
            tv_convert.text=origin.replace(".", "+")
        }
        btn_split.setOnClickListener {
            //按照小数点把字符串分割为字符串数组
            var strList:List<String> = origin.split(".")
            var strResult:String = ""
            for (item in strList) {
                strResult = strResult + item + ", "
            }
            tv_convert.text = strResult
        }
        var number:Int
        btn_cut.setOnClickListener {
            number = et_number.text.toString().toInt()
            //Kotlin的字符串允许通过下标直接访问该位置上的元素
            tv_convert.text = origin[number].toString()
            //字符串也可调用get方法获取指定位置上的字符
            //tv_convert.text = origin.get(number).toString()
        }

        btn_format.setOnClickListener { tv_convert.text = "字符串值为 $origin" }
        //符号$后面跟变量名，系统会自动匹配最长的变量名。
        //比如下面这个例子，打印出来的是origin_trim，不是origin
        //btn_format.setOnClickListener { tv_convert.text = "字符串值为 $origin_trim" }
        //如果在取值之前还要先运算，则需用大括号把运算表达式给括起来
        btn_length.setOnClickListener { tv_convert.text = "字符串长度为 ${origin.length}" }
        //在Kotlin中，美元符号$属于特殊字符，不能直接打印，必须经过转义才行
        btn_dollar.setOnClickListener { tv_convert.text = "美元金额为 ${'$'}$origin" }
        //如果只是对单个特殊字符做转义，也可直接用反斜杆。
        //btn_dollar.setOnClickListener { tv_convert.text = "美元金额为 \$$origin" }
        //${'***'}的好处是能够保留一个字符串中的所有特殊字符

    }
}
