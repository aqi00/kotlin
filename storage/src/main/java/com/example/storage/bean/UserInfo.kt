package com.example.storage.bean

data class UserInfo(var rowid: Long=0, var xuhao: Int=0, var name: String="", var age: Int=0,
                    var height: Long=0, var weight: Float=0f, var married: Boolean=false,
                    var update_time: String="", var phone: String="", var password: String="") {
}
