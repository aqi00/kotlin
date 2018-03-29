package com.example.grammar.animal

import android.util.Log

/**
 * Created by ouyangshen on 2017/8/8.
 */
class Animal {
    //类的初始化函数
    init {
        //Kotlin使用println替换Java的System.out.println
        println("Animal：这是个动物的类")
        //调用Android的日志类Log
        Log.d("Animal", "这是个动物的类")
    }
}