package com.example.storage.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

import com.example.storage.bean.CartInfo
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

class CartDBHelper(var context: Context, private var DB_VERSION: Int=CURRENT_VERSION) : ManagedSQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private val TAG = "CartDBHelper"
        val DB_NAME = "cart.db" //数据库名称
        val TABLE_NAME = "cart_info" //表名称
        var CURRENT_VERSION = 1 //当前的最新版本，如有表结构变更，该版本号要加一
        private var instance: CartDBHelper? = null
        @Synchronized
        fun getInstance(ctx: Context, version: Int=0): CartDBHelper {
            if (instance == null) {
                //如果调用时没传版本号，就使用默认的最新版本号
                instance = if (version>0) CartDBHelper(ctx.applicationContext, version)
                            else CartDBHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "onCreate")
        val drop_sql = "DROP TABLE IF EXISTS $TABLE_NAME;"
        Log.d(TAG, "drop_sql:" + drop_sql)
        db.execSQL(drop_sql)
        val create_sql = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "goods_id LONG NOT NULL," + "count INTEGER NOT NULL," +
            "update_time VARCHAR NOT NULL" + ");"
        Log.d(TAG, "create_sql:" + create_sql)
        db.execSQL(create_sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    fun delete(condition: String): Int {
        var count = 0
        use {
            count = delete(TABLE_NAME, condition, null)
        }
        return count
    }

    fun insert(infoArray: MutableList<CartInfo>): Long {
        var result: Long = -1
        for (i in infoArray.indices) {
            val info = infoArray[i]
            // 如果存在相同rowid的记录，则更新记录
            if (info.rowid > 0) {
                val condition = "rowid='${info.rowid}'"
                update(info, condition)
                result = info.rowid
                continue
            }
            // 不存在唯一性重复的记录，则插入新记录
            val cv = ContentValues()
            cv.put("goods_id", info.goods_id)
            cv.put("count", info.count)
            cv.put("update_time", info.update_time)
            use {
                result = insert(TABLE_NAME, "", cv)
            }
            // 添加成功后返回行号，失败后返回-1
            if (result == -1L) {
                return result
            }
        }
        return result
    }

    @JvmOverloads
    fun update(info: CartInfo, condition: String = "rowid=${info.rowid}"): Int {
        val cv = ContentValues()
        cv.put("goods_id", info.goods_id)
        cv.put("count", info.count)
        cv.put("update_time", info.update_time)
        var count = 0
        use {
            count = update(TABLE_NAME, cv, condition, null)
        }
        return count
    }

    fun query(condition: String): MutableList<CartInfo> {
        val sql = "select rowid,_id,goods_id,count,update_time from $TABLE_NAME where $condition;"
        Log.d(TAG, "query sql: " + sql)
        val infoArray = mutableListOf<CartInfo>()
        use {
            val cursor = rawQuery(sql, null)
            if (cursor.moveToFirst()) {
                while (true) {
                    val info = CartInfo()
                    info.rowid = cursor.getLong(0)
                    info.xuhao = cursor.getInt(1)
                    info.goods_id = cursor.getLong(2)
                    info.count = cursor.getInt(3)
                    info.update_time = cursor.getString(4)
                    infoArray.add(info)
                    if (cursor.isLast) {
                        break
                    }
                    cursor.moveToNext()
                }
            }
            cursor.close()
        }
        return infoArray
    }

    fun queryByRowid(rowid: Long): CartInfo {
        val infoArray = query("rowid='$rowid'")
        val info = if (infoArray.size>0) infoArray[0] else CartInfo()
        return info
    }

    fun queryByGoodsId(goods_id: Long): CartInfo {
        val infoArray = query("goods_id='$goods_id'")
        val info = if (infoArray.size>0) infoArray[0] else CartInfo()
        return info
    }

    fun deleteAll(): Int = delete("1=1")

    fun insert(info: CartInfo): Long = insert(mutableListOf<CartInfo>(info))

    fun queryAll(): MutableList<CartInfo> = query("1=1")

}
