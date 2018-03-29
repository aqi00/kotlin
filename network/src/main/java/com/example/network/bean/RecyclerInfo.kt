package com.example.network.bean

import com.example.network.R

data class RecyclerInfo(var pic_id: Int=0, var title: String="", var desc: String="", var pressed: Boolean=false) {
    var id: Int = 0

    init {
        this.id = RecyclerInfo.seq
        RecyclerInfo.seq++
    }

    companion object {
        private var seq = 0

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

    }

}
