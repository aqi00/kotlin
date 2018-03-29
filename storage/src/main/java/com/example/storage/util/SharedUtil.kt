package com.example.storage.util

import android.content.Context
import android.content.SharedPreferences

class SharedUtil {

    fun writeShared(key: String, value: String) {
        val editor = mShared!!.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun readShared(key: String, defaultValue: String): String? {
        return mShared!!.getString(key, defaultValue)
    }

    companion object {

        private var mUtil: SharedUtil? = null
        private var mShared: SharedPreferences? = null

        fun getIntance(ctx: Context): SharedUtil {
            if (mUtil == null) {
                mUtil = SharedUtil()
            }
            mShared = ctx.getSharedPreferences("share", Context.MODE_PRIVATE)
            return mUtil!!
        }
    }

}
