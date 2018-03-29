package com.example.network.provider

import com.example.network.database.UserDBHelper

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class UserInfoProvider : ContentProvider() {
    lateinit var userDB: UserDBHelper

    //删除数据
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        var count = 0
        if (uriMatcher.match(uri) == USER_INFO) {
            count = userDB.delete(selection, selectionArgs)
        }
        return count
    }

    //插入数据
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        var newUri = uri
        if (uriMatcher.match(uri) == USER_INFO) {
            // 向指定的表插入数据，得到返回的Id
            val rowId = userDB.insert(values)
            if (rowId > 0) { // 判断插入是否执行成功
                // 如果添加成功，利用新添加的Id和生成新的地址
                newUri = ContentUris.withAppendedId(UserInfoContent.CONTENT_URI, rowId)
                // 通知监听器，数据已经改变
                context.contentResolver.notifyChange(newUri, null)
            }
        }
        return newUri
    }

    //创建ContentProvider时调用的回调函数
    override fun onCreate(): Boolean {
        userDB = UserDBHelper.getInstance(context, 1)
        return false
    }

    //查询数据库
    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        var cursor: Cursor? = null
        if (uriMatcher.match(uri) == USER_INFO) {
            val db = userDB.readableDatabase
            // 执行查询
            cursor = db.query(UserInfoContent.TABLE_NAME,
                    projection, selection, selectionArgs, null, null, sortOrder)
            // 设置监听
            cursor?.setNotificationUri(context.contentResolver, uri)
        }
        return cursor
    }

    //获取数据访问类型，暂未实现
    override fun getType(uri: Uri): String? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    //更新数据，暂未实现
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        throw UnsupportedOperationException("Not yet implemented")
    }

    companion object {
        val USER_INFO = 1
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        //伴生对象的初始化操作
        init {
            uriMatcher.addURI(UserInfoContent.AUTHORITIES, "/user", USER_INFO)
        }
    }

}
