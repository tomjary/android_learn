package com.team.sqlitetransaction

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * @author: Administrator
 * @date: 2020/8/31 18:28
 */
class MyOpenHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table info (_id integer primary key autoincrement,name varchar(20),phone varchar(20),money varchar(20))");
        db.execSQL("insert into info ('name','phone','money') values ('张三','138888','2000')");
        db.execSQL("insert into info ('name','phone','money') values ('李四','139999','5000')");
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}