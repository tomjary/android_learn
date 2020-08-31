package com.team.sqlitetransaction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import org.jetbrains.anko.toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var myOpenHelper: MyOpenHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myOpenHelper = MyOpenHelper(this, "sql_tran.db", null, 1)
    }

    fun tran(view: View) {
        val db = myOpenHelper!!.writableDatabase
        // 开启一个事务
        db.beginTransaction()
        try {
            db.execSQL("update info set money=money-200 where name=?", arrayOf("张三"))
            db.execSQL("update info set money=money+200 where name=?", arrayOf("李四"))
            // 所有操作都完成之后调用setTransactionSuccessful,标记事务成功
            db.setTransactionSuccessful()
            toast("成功")
        } catch (e: Exception) {
            // sql执行中出错了
            toast("操作出错了.")
        } finally {
            // 结束事务,如果有setTransactionSuccessful(),那么就提交事务,如果没有,那么就回滚操作
            db.endTransaction()
            // 关闭database
            db.close()
        }


    }
}
