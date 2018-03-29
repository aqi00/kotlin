package com.example.storage.database

import com.example.storage.bean.UserInfo

import android.content.ContentValues
import android.content.Context
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
            //演示数据库升级时要先把下面这行注释
            ",phone VARCHAR" + ",password VARCHAR" + ");"
        Log.d(TAG, "create_sql:" + create_sql)
        db.execSQL(create_sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "onUpgrade oldVersion=$oldVersion, newVersion=$newVersion")
        if (newVersion > 1) {
            //Android的ALTER命令不支持一次添加多列，只能分多次添加
            var alter_sql = "ALTER TABLE $TABLE_NAME ADD COLUMN phone VARCHAR;"
            Log.d(TAG, "alter_sql:" + alter_sql)
            db.execSQL(alter_sql)
            alter_sql = "ALTER TABLE $TABLE_NAME ADD COLUMN password VARCHAR;"
            Log.d(TAG, "alter_sql:" + alter_sql)
            db.execSQL(alter_sql)
        }
    }

    fun delete(condition: String): Int {
        var count = 0
        use {
            count = delete(TABLE_NAME, condition, null)
        }
        return count
    }

    fun insert(info: UserInfo): Long {
        val infoArray = mutableListOf(info)
        return insert(infoArray)
    }

    fun insert(infoArray: MutableList<UserInfo>): Long {
        var result: Long = -1
        for (i in infoArray.indices) {
            val info = infoArray[i]
            var tempArray: List<UserInfo>
            // 如果存在同名记录，则更新记录
            // 注意条件语句的等号后面要用单引号括起来
            if (info.name.isNotEmpty()) {
                val condition = "name='${info.name}'"
                tempArray = query(condition)
                if (tempArray.size > 0) {
                    update(info, condition)
                    result = tempArray[0].rowid
                    continue
                }
            }
            // 如果存在同样的手机号码，则更新记录
            if (info.phone.isNotEmpty()) {
                val condition = "phone='${info.phone}'"
                tempArray = query(condition)
                if (tempArray.size > 0) {
                    update(info, condition)
                    result = tempArray[0].rowid
                    continue
                }
            }
            // 不存在唯一性重复的记录，则插入新记录
            val cv = ContentValues()
            cv.put("name", info.name)
            cv.put("age", info.age)
            cv.put("height", info.height)
            cv.put("weight", info.weight)
            cv.put("married", info.married)
            cv.put("update_time", info.update_time)
            cv.put("phone", info.phone)
            cv.put("password", info.password)
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
    fun update(info: UserInfo, condition: String = "rowid=${info.rowid}"): Int {
        val cv = ContentValues()
        cv.put("name", info.name)
        cv.put("age", info.age)
        cv.put("height", info.height)
        cv.put("weight", info.weight)
        cv.put("married", info.married)
        cv.put("update_time", info.update_time)
        cv.put("phone", info.phone)
        cv.put("password", info.password)
        var count = 0
        use {
            count = update(TABLE_NAME, cv, condition, null)
        }
        return count
    }

    fun query(condition: String): List<UserInfo> {
        val sql = "select rowid,_id,name,age,height,weight,married,update_time,phone,password from $TABLE_NAME where $condition;"
        Log.d(TAG, "query sql: " + sql)
        var infoArray = mutableListOf<UserInfo>()
        use {
            val cursor = rawQuery(sql, null)
            if (cursor.moveToFirst()) {
                while (true) {
                    val info = UserInfo()
                    info.rowid = cursor.getLong(0)
                    info.xuhao = cursor.getInt(1)
                    info.name = cursor.getString(2)
                    info.age = cursor.getInt(3)
                    info.height = cursor.getLong(4)
                    info.weight = cursor.getFloat(5)
                    //SQLite没有布尔型，用0表示false，用1表示true
                    info.married = if (cursor.getInt(6) == 0) false else true
                    info.update_time = cursor.getString(7)
                    info.phone = cursor.getString(8)
                    info.password = cursor.getString(9)
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

    fun queryByPhone(phone: String): UserInfo {
        val infoArray = query("phone='$phone'")
        val info: UserInfo = if (infoArray.size>0) infoArray[0] else UserInfo()
        return info
    }

    fun deleteAll(): Int = delete("1=1")

    fun queryAll(): List<UserInfo> = query("1=1")

}
