package com.team520.imagedemo

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.imageBitmap
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast
import kotlin.math.max
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    var screenWidth: Int = 1
    var screenHeight: Int = 1
    val path = "/mnt/sdcard/Pictures/QQ/fj.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_loadpic.onClick {
            loadtomcat()
            // loadpic2()
        }
    }

    fun loadtomcat() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.tomcat)
        // iv_pic2.imageBitmap = bitmap

        // 使用原图创建一个可以修改的bitmap对象,第一个参数是图片宽,第二个是图片高,第三个是图片的配置信息
        val createBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
        // 创建canvas
        val canvas = Canvas(createBitmap)
        // 通过Matrix可以对图片进行处理
        val matrix = Matrix()
        // Paint 画笔
        val paint = Paint()
        // 执行完这个方法,原图的内容就会画到创建的空白的图片上
        canvas.drawBitmap(bitmap, matrix, paint)
        for (i in 0 until 30) {
            createBitmap.setPixel(30 + i, 30 + i, Color.RED)
        }
        iv_pic2.imageBitmap = createBitmap
    }

    fun loadpic() {
        val options = BitmapFactory.Options()
        // 压缩图片比例
        options.inSampleSize = 2
        val bitmap = BitmapFactory.decodeFile(path, options)
        iv_pic.imageBitmap = bitmap
    }

    fun loadpic2() {
        // 1.获取屏幕的宽高
        // 获取屏幕的宽高
        // val screenWidth = windowManager.defaultDisplay.width
        // val screenHeight = windowManager.defaultDisplay.height
        val point = Point()
        windowManager.defaultDisplay.getSize(point)
        screenWidth = point.x
        screenHeight = point.y
        // 2.获取图片的宽高
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        val bitmap = BitmapFactory.decodeFile(path, options)
        val picWidth = options.outWidth
        val picHeight = options.outHeight
        runOnUiThread { toast("图片的宽${picWidth}高${picHeight}") }
        // 3.计算比例
        val widthIndex = (screenWidth / picWidth).toFloat().roundToInt()
        val heightIndex = (screenHeight / picHeight).toFloat().roundToInt()
        if (picWidth > screenWidth || picHeight > screenHeight) {
            options.inSampleSize = max(widthIndex, heightIndex)
            runOnUiThread { toast("比例是${max(widthIndex, heightIndex)}") }
        }
        // 用计算好的比例加载图片
        options.inJustDecodeBounds = false
        val bitmap1 = BitmapFactory.decodeFile(path, options)
        iv_pic.imageBitmap = bitmap1
    }

}
