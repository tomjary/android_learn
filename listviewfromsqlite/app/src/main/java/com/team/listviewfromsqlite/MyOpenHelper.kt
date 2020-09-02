package com.team.listviewfromsqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * @author: Administrator
 * @date: 2020/9/2 14:36
 */
class MyOpenHelper(context: Context?) : SQLiteOpenHelper(context, "sqlite_demo", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE info (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),phone VARCHAR(20));")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}