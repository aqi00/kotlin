package com.example.complex.bean

import com.example.complex.R

data class RecyclerInfo(var pic_id: Int=0, var title: String="", var desc: String="", var pressed: Boolean=false) {
    var id: Int = 0

    init {
        this.id = RecyclerInfo.seq
        RecyclerInfo.seq++
    }

    companion object {
        private var seq = 0

        private val listImageArray = intArrayOf(R.drawable.public_01, R.drawable.public_02, R.drawable.public_03, R.drawable.public_04, R.drawable.public_05)
        private val listTitleArray = arrayOf(
                "首都日报", "海峡时报", "北方周末", "参照消息", "挨踢杂志")
        private val listDescArray = arrayOf(
                "金秋时节香山染红，市民纷纷登山赏叶",
                "台风接踵而来，出门小心暴雨",
                "俄罗斯老人在东北，生活滋润乐不思蜀",
                "蒙内铁路建成通车，中国标准再下一城",
                "米6大战Mate10，千元智能机谁领风骚")
        val defaultList: MutableList<RecyclerInfo>
            get() {
                val listArray = mutableListOf<RecyclerInfo>()
                for (i in listImageArray.indices) {
                    listArray.add(RecyclerInfo(listImageArray[i], listTitleArray[i], listDescArray[i]))
                }
                return listArray
            }

        private val gridImageArray = intArrayOf(R.drawable.pic_01, R.drawable.pic_02, R.drawable.pic_03, R.drawable.pic_04, R.drawable.pic_05, R.drawable.pic_06, R.drawable.pic_07, R.drawable.pic_08, R.drawable.pic_09, R.drawable.pic_10)
        private val gridTitleArray = arrayOf(
                "商场", "超市", "百货", "便利店", "地摊",
                "食杂店", "饭店", "餐厅", "会所", "菜市场")
        val defaultGrid: MutableList<RecyclerInfo>
            get() {
                val gridArray = mutableListOf<RecyclerInfo>()
                for (i in gridImageArray.indices) {
                    gridArray.add(RecyclerInfo(gridImageArray[i], gridTitleArray[i]))
                }
                return gridArray
            }

        private val stagImageArray = intArrayOf(R.drawable.skirt01, R.drawable.skirt02, R.drawable.skirt03, R.drawable.skirt04, R.drawable.skirt05, R.drawable.skirt06, R.drawable.skirt07, R.drawable.skirt08, R.drawable.skirt09, R.drawable.skirt10, R.drawable.skirt11, R.drawable.skirt12, R.drawable.skirt13, R.drawable.skirt14, R.drawable.skirt15, R.drawable.skirt16, R.drawable.skirt17, R.drawable.skirt18, R.drawable.skirt19, R.drawable.skirt20, R.drawable.skirt21, R.drawable.skirt22, R.drawable.skirt23)
        private val stagTitleArray = arrayOf(
                "促销价", "惊爆价", "跳楼价", "白菜价", "清仓价", "割肉价",
                "实惠价", "一口价", "满意价", "打折价", "腰斩价", "无人问津",
                "算了吧", "大声点", "嘘嘘", "嗯嗯", "呼呼", "呵呵",
                "哈哈", "嘿嘿", "嘻嘻", "嗷嗷", "喔喔")
        val defaultStag: MutableList<RecyclerInfo>
            get() {
                val stagArray = mutableListOf<RecyclerInfo>()
                for (i in stagImageArray.indices) {
                    stagArray.add(RecyclerInfo(stagImageArray[i], stagTitleArray[i]))
                }
                return stagArray
            }

        private val appiImageArray = intArrayOf(R.drawable.dian01, R.drawable.dian02, R.drawable.dian03, R.drawable.dian04, R.drawable.dian05, R.drawable.dian06, R.drawable.dian07, R.drawable.dian08, R.drawable.dian09, R.drawable.dian10, R.drawable.dian11, R.drawable.dian12, R.drawable.dian13, R.drawable.dian14, R.drawable.dian15)
        private val appiTitleArray = arrayOf(
                "双十一", "大聚惠", "爆款价", "就一次", "手慢无", "快点击",
                "付定金", "享特权", "包安装", "再返券", "白送你", "想得美",
                "干活去", "好好学", "才有钱")
        val defaultAppi: MutableList<RecyclerInfo>
            get() {
                val appiArray = mutableListOf<RecyclerInfo>()
                for (i in appiImageArray.indices) {
                    appiArray.add(RecyclerInfo(appiImageArray[i], appiTitleArray[i]))
                }
                return appiArray
            }
    }

}
