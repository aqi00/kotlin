package com.example.custom.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class FreshInfo(var name: String="", var desc: String="", var imageId: Int=0,
                var price: Int=0, var peopleCount: Int=0, var isJoin: Boolean=false) : Parcelable {
}
