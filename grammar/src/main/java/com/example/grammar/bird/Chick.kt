package com.example.grammar.bird

/**
 * Created by ouyangshen on 2017/8/24.
 */
class Chick (name:String, sex:Int) : Bird(name, sex), Behavior {
    override fun fly():String {
        return ""
    }

    override fun swim():String {
        return ""
    }

    override fun run():String {
        return super.run()
    }

    override var skilledSports:String = "游泳"
}