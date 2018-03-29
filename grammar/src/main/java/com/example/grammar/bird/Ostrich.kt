package com.example.grammar.bird

/**
 * Created by ouyangshen on 2017/8/9.
 */
class Ostrich(name:String="鸵鸟", sex:Int = Bird.MALE) : Bird(name, sex) {
    //继承protected的方法，标准写法是“override protected”
    //override protected fun getSexName(sex:Int):String {
    //不过protected的方法继承过来默认就是protected，所以也可直接省略protected
    //override fun getSexName(sex:Int):String {
    //protected的方法继承之后允许将可见性升级为public，但不能降级为private
    override public fun getSexName(sex:Int):String {
        return if(sex==MALE) "雄" else "雌"
    }
}
