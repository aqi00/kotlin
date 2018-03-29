package com.example.grammar.bird

/**
 * Created by ouyangshen on 2017/8/9.
 */
//Kotlin的类默认是不能继承的（即final类型），如果需要继承某类，则该父类应当声明为open类型。
//否则编译器会报错“The type is final, so it cannot be inherited from”。
open class Bird (var name:String, val sex:Int = MALE) {
    //变量、方法、类默认都是public，所以一般都把public省略掉了
    //public var sexName:String
    var sexName:String
    init {
        sexName = getSexName(sex)
    }

    //私有的方法既不能被外部访问，也不能被子类继承，因此open与private不能共存
    //否则编译器会报错：Modifier 'open' is incompatible with 'private'
    //open private fun getSexName(sex:Int):String {
    open protected fun getSexName(sex:Int):String {
        return if(sex==MALE) "公" else "母"
    }

    fun getDesc(tag:String):String {
        return "欢迎来到$tag：这只${name}是${sexName}的。"
    }

    companion object BirdStatic{
        val MALE = 0
        val FEMALE = 1
        val UNKNOWN = -1
        fun judgeSex(sexName:String):Int {
            var sex:Int = when (sexName) {
                "公","雄" -> MALE
                "母","雌" -> FEMALE
                else -> UNKNOWN
            }
            return sex
        }

    }

}