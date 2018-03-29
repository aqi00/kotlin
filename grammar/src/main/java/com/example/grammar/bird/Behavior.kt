package com.example.grammar.bird

/**
 * Created by ouyangshen on 2017/8/9.
 */
//Kotlin与Java一样不允许多重继承，即不能同时继承两个类（及以上类），
//否则编译器报错“Only one class may appear in a supertype list”，
//所以仍然需要接口interface来间接实现多重继承的功能。
//接口不能带构造函数（那样就变成一个类了），否则编译器报错“An interface may not have a constructor”
//interface Behavior(val action:String) {
interface Behavior {
    //接口内部的方法默认就是抽象的，所以不加abstract也可以，当然open也可以不加
    open abstract fun fly():String
    //比如下面这个swim方法就没加关键字abstract，也无需在此处实现方法
    fun swim():String
    //Kotlin的接口与Java的区别在于，Kotlin接口内部允许实现方法，
    //此时该方法不是抽象方法，就不能加上abstract，
    //不过该方法依然是open类型，接口内部的所有方法都默认是open类型
    fun run():String {
        return "大多数鸟儿跑得并不像样，只有鸵鸟、鸸鹋等少数鸟类才擅长奔跑。"
    }
    //Kotlin的接口允许声明抽象属性，实现该接口的类必须重载该属性，
    //与接口内部方法一样，抽象属性前面的open和abstract也可省略掉
    //open abstract var skilledSports:String
    var skilledSports:String
}