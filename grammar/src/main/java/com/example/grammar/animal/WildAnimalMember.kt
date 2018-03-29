package com.example.grammar.animal

/**
 * Created by ouyangshen on 2017/8/8.
 */
class WildAnimalMember (var name:String, val sex:Int = 0) {
    //非空的成员属性必须在声明时赋值或者在构造函数中赋值
    //否则编译器会报错“Property must be initialized or be abstract”
    var sexName:String
    init {
        sexName = if(sex==0) "公" else "母"
    }
}