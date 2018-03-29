package com.example.grammar.animal

import android.content.Context
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/8/8.
 */
class AnimalSeparate {
    constructor(context:Context, name:String) {
        context.toast("这是只$name")
    }

    constructor(context: Context, name:String, sex:Int) {
        var sexName:String = if(sex==0) "公" else "母"
        context.toast("这只${name}是${sexName}的")
    }

}