package com.example.network.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

class UserDBHelper(var context: Context, private var DB_VERSION: Int=CURRENT_VERSION) : ManagedSQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private val TAG = "UserDBHelper"
        var DB_NAME = "user.db" //数据库名称
        var TABLE_NAME = "user_info" //表名称
        var CURRENT_VERSION = 1 //当前的最新版本，如有表结构变更，该版本号要加一
        private var instance: UserDBHelper? = null
        @Synchronized
        fun getInstance(ctx: Context, version: Int=0): UserDBHelper {
            if (instance == null) {
                //如果调用时没传版本号，就使用默认的最新版本号
                instance = if (version>0) UserDBHelper(ctx.applicationContext, version)
                else UserDBHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    private var mDB: SQLiteDatabase? = null

    fun openReadLink(): SQLiteDatabase {
        if (mDB == null || mDB?.isOpen !== true) {
            mDB = instance?.getReadableDatabase()
        }
        return mDB!!
    }

    fun closeLink() {
        if (mDB != null && mDB?.isOpen === true) {
            mDB?.close()
            mDB = null
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "onCreate")
        val drop_sql = "DROP TABLE IF EXISTS $TABLE_NAME;"
        Log.d(TAG, "drop_sql:" + drop_sql)
        db.execSQL(drop_sql)
        val create_sql = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "_id INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL," +
                "name VARCHAR NOT NULL," + "age INTEGER NOT NULL," +
                "height LONG NOT NULL," + "weight FLOAT NOT NULL," +
                "married INTEGER NOT NULL," + "update_time VARCHAR NOT NULL" +
                ",phone VARCHAR" + ",password VARCHAR" + ");"
        Log.d(TAG, "create_sql:" + create_sql)
        db.execSQL(create_sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    fun delete(selection: String?, selectionArgs: Array<String>?): Int {
        var count = 0
        use {
            count = delete(TABLE_NAME, selection, selectionArgs)
        }
        return count
    }

    fun insert(values: ContentValues?): Long {
        var rowId = 0L
        use {
            rowId = insert(TABLE_NAME, null, values)
        }
        return rowId
    }

    fun query(projection: Array<String>?, selection: String?,
              selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        var cursor: Cursor? = null
        use {
            cursor = query(TABLE_NAME,
                    projection, selection, selectionArgs, null, null, sortOrder)
        }
        return cursor
    }

}
