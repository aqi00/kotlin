package com.example.grammar.bird

/**
 * Created by ouyangshen on 2017/8/9.
 */
//注意父类Bird已经在构造函数声明了属性，故而子类Duck无需重复声明属性
//也就是说，子类的构造函数，在输入参数前面不要再加val和var
class Duck(name:String="鸭子", sex:Int = Bird.MALE) : Bird(name, sex) {
}
