package com.team.listviewfromsqlite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SimpleAdapter
import android.widget.TextView
import com.team.listviewfromsqlite.bean.Person
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.*
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    var persons = ArrayList<Person>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myOpenHelper = MyOpenHelper(this)

        btn_insert.setOnClickListener {
            val db = myOpenHelper.writableDatabase
            db?.execSQL("INSERT INTO \"main\".\"info\"(\"name\", \"phone\") VALUES ('fwr', '15112341234');")
            db?.execSQL("INSERT INTO \"main\".\"info\"(\"name\", \"phone\") VALUES ('jary', '18051255125');")
            db?.execSQL("INSERT INTO \"main\".\"info\"(\"name\", \"phone\") VALUES ('tom', '15052015201');")
            db.close()
            toast("插入成功")
        }
        btn_select.setOnClickListener {
            persons.clear()
            val db = myOpenHelper.writableDatabase
            val cursor = db.rawQuery("select * from info;", null)
            while (cursor.moveToNext()) {
                val person = Person()
                person.name = cursor.getString(cursor.getColumnIndex("name"))
                person.phone = cursor.getString(cursor.getColumnIndex("phone"))
                persons.add(person)
            }
            cursor.close()
            db.close()

            // lv_list.adapter = MyAdapter(this)

            val data = ArrayList<Map<String, String>>()

            for (p in persons){
                val hashMap = HashMap<String, String>()
                hashMap["name"] = p.name!!
                hashMap["phone"] = p.phone!!
                data.add(hashMap)
            }

            val simpleAdapter = SimpleAdapter(
                this,
                data,
                R.layout.item,
                arrayOf("name", "phone"),
                intArrayOf(R.id.tv_name, R.id.tv_phone)
            )
            lv_list.adapter = simpleAdapter
        }
    }
    inner class MyAdapter(context: Context): BaseAdapter() {
        private val context = context
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            var view : View? = null
            if (convertView == null) {
                view = View.inflate(context, R.layout.item, null)
            } else {
                view = convertView
            }
            val person = persons[position]
            val tv_name = view?.findViewById<TextView>(R.id.tv_name)
            val tv_phone = view?.findViewById<TextView>(R.id.tv_phone)
            tv_name?.text = person.name
            tv_phone?.text = person.phone
            return view
        }

        override fun getItem(position: Int): Any {
            return persons[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return persons.size
        }

    }
}
