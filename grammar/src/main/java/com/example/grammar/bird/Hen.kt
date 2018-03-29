package com.example.grammar.bird

import com.example.grammar.animal.WildAnimal

/**
 * Created by ouyangshen on 2017/8/9.
 */
class Hen(name:String="鸡", sex:Int = Bird.FEMALE, voice:String="咯咯咯") : Chicken(name, sex, voice) {
    override fun callOut(times: Int): String {
        var count = when {
            times<=0 -> 0
            times>=10 -> 9
            else -> times
        }
        return "$sexName$name${voice}叫了${numberArray[count]}声，原来它下蛋了呀。"
    }
}