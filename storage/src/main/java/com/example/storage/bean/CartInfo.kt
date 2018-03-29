package com.example.storage.bean

data class CartInfo(var rowid: Long=0, var xuhao: Int=0, var goods_id: Long=0,
                    var count: Int=0, var update_time: String="", var goods: GoodsInfo=GoodsInfo()) {
}
