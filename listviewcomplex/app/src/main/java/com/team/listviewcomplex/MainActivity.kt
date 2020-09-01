package com.team.listviewcomplex

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // lv_list.adapter = MyAdapter()
        val arrayAdapter = ArrayAdapter(this, R.layout.item2,
            arrayOf(
                "我是第一个",
                "我是第二个",
                "我是第三个",
                "我是第四个",
                "我是第五个"
            ))
        lv_list.adapter = arrayAdapter

    }

    inner class MyAdapter: BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            // 通过xml文件转为View对象
            // 第一个参数是上下文,第二个参数是条目布局的资源id,第三个参数是ViewGroup(比如LinearLayout )
            // 如果传了第三个参数,那么生成的view就会是他的子View
            var view : View? = null
            if (convertView == null) {
                view = View.inflate(this@MainActivity, R.layout.item, null)

                // 下面几种同样可以
                val inflater = LayoutInflater.from(this@MainActivity)

                val inflater1 = layoutInflater
                // 谷歌的源码中用getSystemService这个方法获取打气筒
                val inflater2 = getSystemService(Context.LAYOUT_INFLATER_SERVICE)

                val view1 = inflater.inflate(R.layout.item, null)


            } else {
                view = convertView
            }
            return view

        }

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return 10
        }

    }

}
