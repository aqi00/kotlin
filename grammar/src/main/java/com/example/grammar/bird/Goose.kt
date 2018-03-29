package com.example.grammar.bird

/**
 * Created by ouyangshen on 2017/8/9.
 */
class Goose(name:String="鹅", sex:Int = Bird.MALE) : Bird(name, sex), Behavior {
    override fun fly():String {
        return "鹅能飞一点点，但飞不高，也飞不远。"
    }

    override fun swim():String {
        return "鹅，鹅，鹅，曲项向天歌。白毛浮绿水，红掌拨清波。"
    }

    //因为接口已经实现了run方法，所以此处可以不用实现该方法，当然你要实现它也行。
    override fun run():String {
        //super用来调用父类的属性或方法，由于Kotlin的接口允许实现方法，因此super所指的对象也可以是interface
        return super.run()
    }

    //重载了来自接口的抽象属性
    override var skilledSports:String = "游泳"
}