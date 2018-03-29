package com.example.grammar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_repeat.*

class RepeatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repeat)

        val poemArray:Array<String> = arrayOf("朝辞白帝彩云间", "千里江陵一日还", "两岸猿声啼不住", "轻舟已过万重山")

        btn_repeat_item.setOnClickListener {
            var poem:String=""
            //循环取出数组中的元素
            for (item in poemArray) {
                poem = "$poem$item，\n"
            }
            tv_poem_content.text = poem
        }
        btn_repeat_subscript.setOnClickListener {
            var poem:String=""
            //根据数组的下标进行轮询
            for (i in poemArray.indices) {
                if (i%2 == 0) {
                    poem = "$poem${poemArray[i]}，\n"
                } else {
                    poem = "$poem${poemArray[i]}。\n"
                }
            }
            tv_poem_content.text = poem
        }
        btn_repeat_begin.setOnClickListener {
            var poem:String=""
            //利用while语句实现“for(初始语句;循环条件;步增语句)”的功能
            var i:Int = 0
            while (i < poemArray.size) {
                if (i%2 ==0) {
                    poem = "$poem${poemArray[i]}，\n"
                } else {
                    poem = "$poem${poemArray[i]}。\n"
                }
                i++
            }
            poem = "${poem}该诗歌一共有${i}句。"
            tv_poem_content.text = poem
        }
        btn_repeat_end.setOnClickListener {
            var poem:String=""
            var i:Int = 0
            do {
                if (i%2 ==0) {
                    poem = "$poem${poemArray[i]}，\n"
                } else {
                    poem = "$poem${poemArray[i]}。\n"
                }
                i++
            } while (i < poemArray.size)
            poem = "${poem}该诗歌一共有${i}句。"
            tv_poem_content.text = poem
        }

        val poem2Array:Array<String?> = arrayOf("朝辞白帝彩云间", null, "千里江陵一日还", "", "两岸猿声啼不住", "   ", "轻舟已过万重山", "送孟浩然之广陵")

        btn_repeat_continue.setOnClickListener {
            var poem:String=""
            var pos:Int=-1
            var count:Int=0
            while (pos <= poem2Array.size) {
                pos++
                //poemArray[i].isNullOrEmpty()
                //poemArray[i].isNullOrBlank()
                //poemArray[i].isEmpty()
                //poemArray[i].isBlank()
                //发现该行是空串或者空格串，则忽略该行
                if (poem2Array[pos].isNullOrBlank())
                    continue
                if (count%2 ==0) {
                    poem = "$poem${poem2Array[pos]}，\n"
                } else {
                    poem = "$poem${poem2Array[pos]}。\n"
                }
                count++
                //合法行数达到四行，则结束循环
                if (count == 4)
                    break
            }
            tv_poem_content.text = poem
        }

        btn_repeat_break.setOnClickListener {
            var i:Int = 0
            var is_found = false
            //给外层循环加个名叫outside的标记
            outside@ while (i < poemArray.size) {
                var j:Int = 0
                var item = poemArray[i];
                while ( j < item.length) {
                    if (item[j] == '一') {
                        is_found = true
                        //发现情况，直接跳出outside循环
                        break@outside
                    }
                    j++
                }
//                //如果内层循环直接跳出两层循环，那么下面的判断语句就不需要了
//                if (is_found)
//                    break
                i++
            }
            tv_poem_content.text = if (is_found) "我找到'一'字啦" else "没有找到'一'字呀"
        }
    }
}
