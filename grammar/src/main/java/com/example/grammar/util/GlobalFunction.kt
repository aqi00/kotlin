package com.example.grammar.util

/**
 * Created by ouyangshen on 2017/8/9.
 */
//Kotlin允许定义全局函数，即函数可在单独的kt文件中定义，然后其他地方也能直接调用
fun <T> appendString(tag:String, vararg otherInfo: T?):String {
    var str:String = "$tag："
    //遍历可变参数中的泛型变量，将其转为字符串再拼接到一起
    for (item in otherInfo) {
        str = "$str${item.toString()}，"
    }
    return str
}

//如果函数内容比较简单，就可以省略大括号。比如下面这个阶乘函数计算n!的结果
fun factorial(n:Int):Int = if (n <= 1) n else n* factorial(n - 1)

//如果函数尾部递归调用自身，则可加上关键字tailrec表示这是个尾递归函数，
//此时编译器会自动优化递归，即用循环方式代替递归，从而避免栈溢出的情况。
//比如下面这个求余弦不动点的函数就是尾递归函数
tailrec fun findFixPoint(x: Double = 1.0): Double
        = if (x == Math.cos(x)) x else findFixPoint(Math.cos(x))

//允许将函数表达式作为输入参数传进来，就形成了高阶函数，这里的greater函数就像是个变量。
//greater函数有两个输入参数，返回布尔型的输出参数。
//如果第一个参数大于第二个参数，则认为greater返回true，否则返回false。
fun <T> maxCustom(array: Array<T>, greater: (T, T) -> Boolean): T? {
    var max: T? = null
    for (item in array)
        if (max == null || greater(item, max))
            max = item
    return max
}
