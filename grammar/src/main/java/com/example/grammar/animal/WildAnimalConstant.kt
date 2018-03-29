package com.example.grammar.animal

/**
 * Created by ouyangshen on 2017/8/8.
 */
class WildAnimalConstant(var name:String, val sex:Int = MALE) {
    var sexName:String
    init {
        sexName = if(sex==MALE) "公" else "母"
    }

    fun getDesc(tag:String):String {
        return "欢迎来到$tag：这只${name}是${sexName}的。"
    }

    companion object WildAnimal{
        //静态常量的值是不可变的，所以要使用关键字val修饰
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