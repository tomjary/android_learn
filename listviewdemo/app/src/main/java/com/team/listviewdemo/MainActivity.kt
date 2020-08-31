package com.team.listviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lv_list.adapter = MyAdapter()
    }

    inner class MyAdapter: BaseAdapter() {
        override fun getCount(): Int {
            // 返回值决定了ListView展示多少条数据
            return 10
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var textView : TextView? = null
            if (convertView == null) {
                Log.d("convertView","getView:创建新的对象${position}")
                // 说明没有可以重用的View对象,需要创建新的View对象
                textView = TextView(this@MainActivity)
            } else {
                Log.d("convertView","getView:复用对象${position}")
                // 当前的convertView可以被重新使用
                textView = convertView as TextView
            }
            // 手动创建View并返回
            textView.setText("我是第${position}个条目")
            return textView
        }

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return 0
        }


    }
}
