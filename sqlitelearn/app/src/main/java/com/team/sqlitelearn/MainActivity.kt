package com.team.sqlitelearn

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myOpenHelper = MyOpenHelper(this, "sqlite_test", null, 1)
        //val readableDatabase = myOpenHelper.readableDatabase


        bt_insert.setOnClickListener {
            // 获取SQLiteDatabase对象
            val database = myOpenHelper.writableDatabase
            var sql = "INSERT INTO info(name, phone) VALUES ('xiaohong', '15114725836');"
            // 执行sql
            database.execSQL(sql)
            sql = "INSERT INTO info(name, phone) VALUES ('lisi', '15252134152');"
            database.execSQL(sql)
            // 调用close方法
            database.close()
            toast("增加成功")
        }

        bt_update.setOnClickListener {
            val database = myOpenHelper.writableDatabase
            val sql = "UPDATE info SET \"name\" = 'fwr_update', \"phone\" = '15131234212' WHERE \"name\" = 'fwr';"
            database.execSQL(sql)
            database.close()
            toast("更新成功")
        }

        bt_select.setOnClickListener {
            val database = myOpenHelper.writableDatabase
            val cursor = database.rawQuery("SELECT * FROM info;", null)
            // rawQuery()返回的是一个游标，通过游标可以访问整个结果集
            // 游标默认指向所有结果之前的一行，可以通过moveToNext()方法移动游标
            while (cursor.moveToNext()){
                // 通过列的索引获取数据
                val name = cursor.getString(1)
                // 通过列名获取数据
                val phone = cursor.getString(cursor.getColumnIndex("phone"))
                Log.d("SQLITE","onCreate:name:$name----phone:$phone")
                toast("name:$name----phone:$phone")
            }
            // 关闭游标
            cursor.close()
            toast("查询成功")
            database.close()
        }

        bt_delete.setOnClickListener {
            val database = myOpenHelper.writableDatabase
            database.execSQL("DELETE FROM \"main\".\"info\" WHERE \"name\" = 'xiaohong';")
            database.close()
            toast("删除成功")
        }

        /****************************************************************************************/
        bt_delete2.setOnClickListener {
            val database = myOpenHelper.writableDatabase
            // 表名
            val table = "info"
            // where的条件,这里不能包含where, =后面的条件要用?代替
            val whereClause = "name=?"
            val whereArgs = arrayOf("xiaohong")
            val deleteNums = database.delete(table, whereClause, whereArgs)
            toast("删除了${deleteNums}条数据")
            // 关闭
            database.close()
        }
        bt_insert2.setOnClickListener {
            val database = myOpenHelper.writableDatabase

            // nullColumnHack传入可为空的列名. 万一程序生成了一个insert into null values null的sql,nullColumnHack的值会替换到null里.
            // insert into nullColumnHack values null
            val nullColumnHack = null

            // ContentValues() 封装了一行的值,通过put方法给每一列赋值,key对应列名,value对应值
            val values = ContentValues()
            values.put("name", "冯文瑞")
            values.put("phone", "15137349065")
            val insertId = database.insert("info", nullColumnHack, values)
            if (insertId != -1L) {
                toast("插入的id是$insertId")
            }else {
                toast("插入失败")
            }
            database.close()
        }
        bt_select2.setOnClickListener {
            val database = myOpenHelper.writableDatabase
            val queryCursor = database.query(
                "info",
                arrayOf("name", "phone"),
                "name=?",
                arrayOf("冯文瑞"),
                null,
                null,
                null
            )
            while (queryCursor.moveToNext()) {
                val name = queryCursor.getString(queryCursor.getColumnIndex("name"))
                val phone = queryCursor.getString(queryCursor.getColumnIndex("phone"))
                toast("name=$name,phone=$phone")
            }
            queryCursor.close()
            database.close()
        }
        bt_update2.setOnClickListener {
            val database = myOpenHelper.writableDatabase
            val values = ContentValues()
            values.put("name", "李四")
            values.put("phone", "15100001234")
            val updateNums = database.update("info", values, "name=?", arrayOf("冯文瑞"))
            toast("更新了${updateNums}条数据")
            database.close()
        }


    }
}
