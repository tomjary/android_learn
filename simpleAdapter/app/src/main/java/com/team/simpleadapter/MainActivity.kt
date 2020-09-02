package com.team.simpleadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val data = ArrayList<Map<String, String>>()
        val map = HashMap<String, String>()
        map["title"] = "2020年S10总决赛即将打响"
        map["content"] = "10月份即将在上海举办S10全球总决赛"
        data.add(map)
        val map2 = HashMap<String, String>()
        map2["title"] = "7月14日比赛服下载地址更新公告"
        map2["content"] = "10.13版本已启用，请比赛服使用方提前下载新版本，以保证比赛的正常进行。"
        data.add(map2)

        val from = arrayOf("title", "content")
        val to = intArrayOf(R.id.tv_title, R.id.tv_content)

        /**
         * 第一个参数:上下文.
         * 第二个参数:要显示的数据,每个条目的数据放到一个Map中,再放到list里
         * 第三个参数:布局ID
         * 第四个参数:string数组,每个元素都是要展示数据的map中的key.
         * 第五个参数:int数组,存放的是要展示数据的控件id,每个id和第四个参数中的元素要对应起来
         */
        val simpleAdapter = SimpleAdapter(this, data, R.layout.item, from, to)
        lv_list.adapter = simpleAdapter

    }
}
