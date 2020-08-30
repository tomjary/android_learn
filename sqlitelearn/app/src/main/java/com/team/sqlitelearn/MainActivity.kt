package com.team.sqlitelearn

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myOpenHelper = MyOpenHelper(this, "sqlite_test", null, 1)
        //val readableDatabase = myOpenHelper.readableDatabase


        bt_insert.setOnClickListener {
            val database = myOpenHelper.writableDatabase
            database.execSQL("INSERT INTO \"main\".\"info\"(\"name\", \"phone\") VALUES ('xiaomei', '15112345213');")
            toast("增加成功")
            database.close()
        }
        bt_update.setOnClickListener {
            val database = myOpenHelper.writableDatabase
            database.execSQL("UPDATE \"main\".\"info\" SET \"name\" = 'xiaomei_update', \"phone\" = '15131234212' WHERE \"name\" = 'xiaomei';")
            toast("修改成功")
            database.close()
        }
        bt_select.setOnClickListener {
            val database = myOpenHelper.writableDatabase
            database.execSQL("SELECT * FROM \"info\" WHERE _id = 1;")
            toast("查询成功")
            database.close()
        }
        bt_delete.setOnClickListener {
            val database = myOpenHelper.writableDatabase
            database.execSQL("DELETE FROM \"main\".\"info\" WHERE _id = 1")
            toast("删除成功")
            database.close()
        }
    }
}
