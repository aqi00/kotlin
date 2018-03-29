package com.example.network.provider

import com.example.network.database.UserDBHelper

import android.net.Uri
import android.provider.BaseColumns

class UserInfoContent : BaseColumns {
    companion object {
        // 这里的名称必须与AndroidManifest.xml里的android:authorities保持一致
        val AUTHORITIES = "com.example.network.provider.UserInfoProvider"
        // 表名
        val TABLE_NAME = UserDBHelper.TABLE_NAME
        // 访问该内容提供器的URI
        val CONTENT_URI = Uri.parse("content://$AUTHORITIES/user")
        //	// 该内容提供器返回的数据类型定义
        //	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.myprovider.user";
        //	public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd.myprovider.user";
        // 列名
        val USER_NAME = "name"
        val USER_AGE = "age"
        val USER_HEIGHT = "height"
        val USER_WEIGHT = "weight"
        val USER_MARRIED = "married"
        // 默认的排序方法
        val DEFAULT_SORT_ORDER = "_id desc"
    }
}
