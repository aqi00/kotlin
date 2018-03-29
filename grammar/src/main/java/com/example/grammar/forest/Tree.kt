package com.example.grammar.forest

/**
 * Created by ouyangshen on 2017/8/18.
 */
class Tree(var treeName:String) {

    //在类内部再定义一个类，这个新类称作嵌套类
    class Flower (var flowerName:String) {
        fun getName():String {
            return "这是一朵$flowerName"
            //普通的嵌套类不能访问外部类的成员如treeName
            //否则编译器报错“Unresolved reference: ***”
            //return "这是${treeName}上的一朵$flowerName"
        }
    }

    //嵌套类加上了inner前缀，就成为了内部类
    inner class Fruit (var fruitName:String) {
        fun getName():String {
            //只有声明为内部类（添加了关键字inner），才能访问外部类的成员
            return "这是${treeName}长出来的$fruitName"
        }
    }
}