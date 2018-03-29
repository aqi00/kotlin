package com.example.grammar.animal

import android.content.Context
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/8/8.
 */
//类的主构造函数使用了默认参数
//class AnimalDefault (context: Context, name:String, sex:Int = 0) {
//加上@JvmOverloads的目的，是让Java代码也能识别默认参数
//因为添加了注解标记，所以必须显示关键字constructor
class AnimalDefault @JvmOverloads constructor(context: Context, name:String, sex:Int = 0) {
    init {
        var sexName:String = if(sex==0) "公" else "母"
        context.toast("这只${name}是${sexName}的")
    }
}