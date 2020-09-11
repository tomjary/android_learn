package com.team520.dialogdemo

import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 普通对话框
        btn_normal.onClick {
            // 创建builder
            val builder = AlertDialog.Builder(this@MainActivity)
            // 给对话框设置一个标题
            builder.setTitle("普通对话框")
            // 设置内容
            builder.setMessage("这里显示对话框内容")
            builder.setPositiveButton("确定", DialogInterface.OnClickListener { dialogInterface, i ->
                toast("点击了确定按钮")
            })
            builder.setNegativeButton("取消", DialogInterface.OnClickListener { dialogInterface, i ->
                toast("点击了取消按钮")
            })
            builder.show()
        }
        // 单选对话框
        btn_single_choice.onClick {
            // 创建builder
            val builder = AlertDialog.Builder(this@MainActivity)
            // 给对话框设置一个标题
            builder.setTitle("你喜欢的运动是?")
            val items = arrayOf("读书", "打游戏", "LOL", "CSGO", "睡觉")
            builder.setSingleChoiceItems(items, -1, DialogInterface.OnClickListener { dialogInterface, i ->
                toast("你选择的是${items[i]}")
                dialogInterface.dismiss()
            })
            builder.show()
        }
        // 多选对话框
        btn_multiple_choice.onClick {
            // 创建builder
            val builder = AlertDialog.Builder(this@MainActivity)
            // 给对话框设置一个标题
            builder.setTitle("你喜欢的水果是?")
            val items = arrayOf("西瓜", "菠萝", "火龙果", "桃子", "苹果")
            val checkedItems = booleanArrayOf(true, false, false, false, false)
            builder.setMultiChoiceItems(items, checkedItems, DialogInterface.OnMultiChoiceClickListener { dialogInterface, index, isCheced ->
                checkedItems[index] = isCheced
            })
            builder.setNegativeButton("确定", DialogInterface.OnClickListener { dialogInterface, i ->
                var checkedString = ""
                for (i in 0 until checkedItems.size) {
                    if (checkedItems[i]) {
                        checkedString = checkedString  + "," + items[i]
                    }
                }
                toast("${checkedString}这些被选中了")
            })
            builder.show()
        }
        // 进度条对话框
        btn_progress.onClick {
            val dialog = ProgressDialog(this@MainActivity)
            // 设置进度样式为水平
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            // 设置标题
            dialog.setTitle("正在加载...")
            // 设置最大进度
            dialog.max = 100
            // 显示对话框
            dialog.show()
            // 开线程模拟下载进度
            Thread(Runnable {
                kotlin.run {
                    for (i in 0 until 100) {
                        dialog.progress = i
                        SystemClock.sleep(100L)
                    }
                    runOnUiThread { toast("下载成功") }
                    dialog.dismiss()
                }
            }).start()
        }

    }
}
