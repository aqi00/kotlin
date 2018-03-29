package com.example.custom.bean

import com.example.custom.R

data class GoodsInfo(var rowid: Long=0, var xuhao: Int=0, var name: String="", var desc: String="", var price: Int=0,
                     var thumb_path: String="", var pic_path: String="", var thumb: Int=0, var pic: Int=0) {

    companion object {
        private val mNameArray = arrayOf(
                "iPhone8", "Mate10", "小米6", "OPPO R11", "vivo X9S", "魅族Pro6S")
        private val mDescArray = arrayOf(
                "Apple iPhone 8 256GB 玫瑰金色 移动联通电信4G手机",
                "华为 HUAWEI Mate10 6GB+128GB 全网通（香槟金）",
                "小米 MI6 全网通版 6GB+128GB 亮白色",
                "OPPO R11 4G+64G 全网通4G智能手机 玫瑰金",
                "vivo X9s 4GB+64GB 全网通4G拍照手机 玫瑰金",
                "魅族 PRO6S 4GB+64GB 全网通公开版 星空黑 移动联通电信4G手机")
        private val mPriceArray = intArrayOf(6888, 3999, 2999, 2899, 2698, 2098)
        private val mThumbArray = intArrayOf(R.drawable.iphone_s, R.drawable.huawei_s, R.drawable.xiaomi_s, R.drawable.oppo_s, R.drawable.vivo_s, R.drawable.meizu_s)
        private val mPicArray = intArrayOf(R.drawable.iphone, R.drawable.huawei, R.drawable.xiaomi, R.drawable.oppo, R.drawable.vivo, R.drawable.meizu)
        val defaultList: MutableList<GoodsInfo>
            get() {
                val goodsList = mutableListOf<GoodsInfo>()
                for (i in mNameArray.indices) {
                    val info = GoodsInfo(name=mNameArray[i], desc=mDescArray[i],
                            price=mPriceArray[i], thumb=mThumbArray[i], pic=mPicArray[i])
                    goodsList.add(info)
                }
                return goodsList
            }
    }

}
