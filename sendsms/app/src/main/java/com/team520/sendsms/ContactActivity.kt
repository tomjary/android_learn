package com.team520.sendsms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.team520.sendsms.bean.Contact
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : AppCompatActivity() {

    private val contacts = ArrayList<Contact>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        // 创建联系人数据
        for (i in 1..10) {
            val c = Contact("张三$i", "150123452$i")
            contacts.add(c)
        }

        lv_contact.adapter = MyAdapter()

        lv_contact.setOnItemClickListener { adapterView, view, position, id ->
            // 获取电话
            val phone = contacts[position].phone
            val data = Intent()
            data.putExtra("phone", phone)
            setResult(Constants.CONTACT_REQUEST_CODE, data)
            // 关闭页面
            finish()
            // 传回数据
        }


    }

    inner class MyAdapter : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, p2: ViewGroup?): View {
            val view: View? = convertView ?: View.inflate(this@ContactActivity, R.layout.item_contact, null)
            val contact = contacts[position]
            val tvName = view?.findViewById<TextView>(R.id.tv_name)
            val tvPhone = view?.findViewById<TextView>(R.id.tv_phone)
            tvName?.text = contact.name
            tvPhone?.text = contact.phone
            return view!!
        }

        override fun getItem(position: Int): Any {
            return contacts[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return contacts.size
        }

    }
}


