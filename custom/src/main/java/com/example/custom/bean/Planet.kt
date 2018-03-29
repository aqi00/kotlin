package com.example.custom.bean

import com.example.custom.R

data class Planet(var image: Int, var name: String, var desc: String) {
    companion object {
        private val iconArray = intArrayOf(R.drawable.shuixing, R.drawable.jinxing, R.drawable.diqiu, R.drawable.huoxing, R.drawable.muxing, R.drawable.tuxing)
        private val nameArray = arrayOf("水星", "金星", "地球", "火星", "木星", "土星")
        private val descArray = arrayOf("水星是太阳系八大行星最内侧也是最小的一颗行星，也是离太阳最近的行星", "金星是太阳系八大行星之一，排行第二，距离太阳0.725天文单位", "地球是太阳系八大行星之一，排行第三，也是太阳系中直径、质量和密度最大的类地行星，距离太阳1.5亿公里", "火星是太阳系八大行星之一，排行第四，属于类地行星，直径约为地球的53%", "木星是太阳系八大行星中体积最大、自转最快的行星，排行第五。它的质量为太阳的千分之一，但为太阳系中其它七大行星质量总和的2.5倍", "土星为太阳系八大行星之一，排行第六，体积仅次于木星")
        val defaultList: MutableList<Planet>
            get() {
                val planetList = mutableListOf<Planet>()
                for (i in iconArray.indices) {
                    planetList.add(Planet(iconArray[i], nameArray[i], descArray[i]))
                }
                return planetList
            }
    }
}
