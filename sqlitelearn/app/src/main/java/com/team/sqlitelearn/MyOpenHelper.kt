package com.team.sqlitelearn

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * @author: Administrator
 * @date: 2020/8/28 15:38
 */

class MyOpenHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(
    context, // 上下文
    name, // 数据库的名字,如果传入null,就是在内存中创建一个数据库,应用退出后,数据不保存
    factory,  // 游标工厂,如果使用系统默认的游标工厂就传入null
    version // 数据库的版本号,用版本号来控制数据库的升级和降级,从1开始
) {
    /**
     * 数据库第一次创建的时候,会调用这个方法,一般做表结构创建和数据初始化
     */
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE info (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),phone VARCHAR(20));")
        db?.execSQL("INSERT INTO \"main\".\"info\"(\"_id\", \"name\", \"phone\") VALUES (1, 'fwr', '15131234212');")

        Log.d("SQLITE","onCreate:被调用")
    }

    // 升级
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    // 降级
    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}
