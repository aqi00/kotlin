package com.example.storage.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

import com.example.storage.bean.GoodsInfo
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

class GoodsDBHelper(var context: Context, private var DB_VERSION: Int=CURRENT_VERSION) : ManagedSQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private val TAG = "GoodsDBHelper"
        val DB_NAME = "goods.db" //数据库名称
        val TABLE_NAME = "goods_info" //表名称
        var CURRENT_VERSION = 1 //当前的最新版本，如有表结构变更，该版本号要加一
        private var instance: GoodsDBHelper? = null
        @Synchronized
        fun getInstance(ctx: Context, version: Int=0): GoodsDBHelper {
            if (instance == null) {
                //如果调用时没传版本号，就使用默认的最新版本号
                instance = if (version>0) GoodsDBHelper(ctx.applicationContext, version)
                            else GoodsDBHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        val drop_sql = "DROP TABLE IF EXISTS $TABLE_NAME;"
        Log.d(TAG, "onCreate drop_sql:" + drop_sql)
        db.execSQL(drop_sql)
        val create_sql = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "name VARCHAR NOT NULL," + "desc VARCHAR NOT NULL," +
            "price INTEGER NOT NULL," + "thumb_path VARCHAR NOT NULL," +
            "pic_path VARCHAR NOT NULL" + ");"
        Log.d(TAG, "onCreate create_sql:" + create_sql)
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

    fun insert(infoArray: MutableList<GoodsInfo>): Long {
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
            cv.put("name", info.name)
            cv.put("desc", info.desc)
            cv.put("price", info.price)
            cv.put("thumb_path", info.thumb_path)
            cv.put("pic_path", info.pic_path)
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
    fun update(info: GoodsInfo, condition: String = "rowid=${info.rowid}"): Int {
        val cv = ContentValues()
        cv.put("name", info.name)
        cv.put("desc", info.desc)
        cv.put("price", info.price)
        cv.put("thumb_path", info.thumb_path)
        cv.put("pic_path", info.pic_path)
        var count = 0
        use {
            count = update(TABLE_NAME, cv, condition, null)
        }
        return count
    }

    fun query(condition: String): MutableList<GoodsInfo> {
        val sql = "select rowid,_id,name,desc,price,thumb_path,pic_path from $TABLE_NAME where $condition;"
        Log.d(TAG, "query sql: " + sql)
        val infoArray = mutableListOf<GoodsInfo>()
        use {
            val cursor = rawQuery(sql, null)
            if (cursor.moveToFirst()) {
                while (true) {
                    val info = GoodsInfo()
                    info.rowid = cursor.getLong(0)
                    info.xuhao = cursor.getInt(1)
                    info.name = cursor.getString(2)
                    info.desc = cursor.getString(3)
                    info.price = cursor.getInt(4)
                    info.thumb_path = cursor.getString(5)
                    info.pic_path = cursor.getString(6)
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

    fun queryByRowid(rowid: Long): GoodsInfo {
        val infoArray = query("rowid='$rowid'")
        val info = if (infoArray.size>0) infoArray[0] else GoodsInfo()
        return info
    }

    fun deleteAll(): Int = delete("1=1")

    fun insert(info: GoodsInfo): Long = insert(mutableListOf<GoodsInfo>(info))

    fun queryAll(): MutableList<GoodsInfo> = query("1=1")

}
