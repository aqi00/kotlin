package com.example.network.bean

data class UserData(var rowid: Long=0L, var xuhao: Int=0, var name: String="", var age: Int=0,
                    var height: Long=0L, var weight: Float=0F, var married: Boolean=false,
                    var update_time: String="", var phone: String="", var password: String="") {
}
