package com.example.grammar.util

/**
 * Created by ouyangshen on 2017/9/22.
 */

//fun Array<Int>.swap(pos1: Int, pos2: Int) {
//    val tmp = this[pos1] //this表示数组自身
//    this[pos1] = this[pos2]
//    this[pos2] = tmp
//}

//扩展函数结合泛型函数，能够更好地扩展函数功能
fun <T> Array<T>.swap(pos1: Int, pos2: Int) {
    val tmp = this[pos1] //this表示数组对象自身
    this[pos1] = this[pos2]
    this[pos2] = tmp
}

//给高阶函数进行扩展，形成数组的扩展函数
fun <T> Array<T>.maxCustomize(greater: (T, T) -> Boolean): T? {
    var max: T? = null
    for (item in this)
        if (max == null || greater(item, max))
            max = item
    return max
}
