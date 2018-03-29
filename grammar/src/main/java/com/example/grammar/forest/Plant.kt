package com.example.grammar.forest

/**
 * Created by ouyangshen on 2017/8/18.
 */
//数据类必须有主构造函数，且至少有一个输入参数，
//并且要声明与输入参数同名的属性，即输入参数前面添加关键字val或者var，
//数据类不能是基类也不能是子类，不能是抽象类，也不能是内部类，更不能是密封类。
data class Plant(var name:String, var stem:String, var leaf:String, var flower:String, var fruit:String, var seed:String) {
}