package com.example.grammar.bird

/**
 * Created by ouyangshen on 2017/8/24.
 */
class BehaviorRun : Behavior {
    override fun fly():String {
        return "飞不起来"
    }

    override fun swim():String {
        return "望洋兴叹"
    }

    override fun run():String {
        return super.run()
    }

    override var skilledSports:String = "奔跑"
}