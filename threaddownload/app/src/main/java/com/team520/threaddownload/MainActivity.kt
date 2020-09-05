package com.team520.threaddownload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import android.util.Log
import org.jetbrains.anko.toast
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    var downloadUrl: String = "http://kbversion.oss-cn-hangzhou.aliyuncs.com/101/T_101_1.0.1_20191107_kuaibao.apk"
    var blockSize: Int = 1024 * 500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_download.onClick {
            // 清除线性布局里的子View
            ln_progress.removeAllViews()
            if (et_url.text.toString() != "") {
                downloadUrl = et_url.text.toString()
            } else {
                toast("请输入下载链接")
                return@onClick
            }
            val etThreadCount = et_count.text.toString().trim()
            if (etThreadCount != "") {
                val threadCount = etThreadCount.toInt()
                for (i in 0 until threadCount) {
                    View.inflate(applicationContext, R.layout.item, ln_progress)
                }

                Thread(Runnable {
                    try {
                        val url = URL(downloadUrl)
                        val connection = url.openConnection() as HttpURLConnection
                        connection.requestMethod = "GET"
                        connection.connectTimeout = 10000
                        if (connection.responseCode == 200) {
                            // 获取要下载的文件长度
                            val fileLength = connection.contentLength
                            // 在本地创建一个一样大的文件
                            val file = RandomAccessFile(getFileName(downloadUrl), "rw")
                            file.setLength(fileLength.toLong()) // 设置文件大小
                            blockSize = fileLength/threadCount
                            // 计算每个线程要下载的数据范围
                            for (i in 0 until threadCount){
                                val startIndex = i * blockSize
                                var endIndex = (i + 1) * blockSize - 1
                                if (i == threadCount - 1){
                                    // 说明是最后一个线程
                                    endIndex = fileLength - 1
                                }
                                val pb = ln_progress.getChildAt(i) as ProgressBar
                                // 设置进度条的最大进度
                                pb.max = endIndex - startIndex
                                DownLoadThread(startIndex, endIndex, i).start()

                            }

                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }).start()

            } else {
                toast("请输入要下载的线程个数")
                return@onClick
            }

        }
    }

    inner class DownLoadThread(
        var startIndex: Int,
        val endIndex: Int,
        val threadID: Int,
        val pb: ProgressBar = ln_progress.getChildAt(threadID) as ProgressBar
    ) : Thread() {
        override fun run() {
            try {
                //读取出记录下来的位置
                val temp = File(getFileName(downloadUrl) + threadID + ".log")
                if (temp.length() > 0) {
                    // 说明日志文件有内容
                    val fileInputStream = FileInputStream(temp)
                    val bufferedReader = BufferedReader(InputStreamReader(fileInputStream))
                    val readRes = bufferedReader.readLine()
                    // 读出记录下来的位置更新下载请求的起始位置
                    startIndex = readRes.toInt()
                }
                val url = URL(downloadUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 10000
                // 设置Range头
                connection.setRequestProperty("Range", "bytes=$startIndex-$endIndex")
                if (connection.responseCode == 206) {
                    Log.d("THREADDOWNLOAD", "run:线程${threadID}开始下载$startIndex")
                    val inputStream = connection.inputStream
                    var len = -1
                    val buffer = ByteArray(1024 * 500)
                    val file = RandomAccessFile(getFileName(downloadUrl), "rw")

                    //一定不要忘记  要seek到startIndex位置 写入数据
                    file.seek(startIndex.toLong())
                    var count = 0
                    /**
                     * .also { } 的用法
                     * it代指前面的值,len=it的意思就是把it赋值给len
                     */
                    while (inputStream.read(buffer).also { len = it } != -1) {
                        file.write(buffer, 0, len)
                        // 计算当前线程下载了多少
                        count += len
                        // 计算当前线程总共下载了多少
                        val position = count + startIndex
                        // 设置进度条的位置
                        pb.progress = position - threadID * blockSize
                        // 用一个文件记录这个位置,以便于断点续传
                        val tempFile = RandomAccessFile(getFileName(downloadUrl), "rwd")
                        tempFile.write(position)
                        tempFile.close()
                    }
                    file.close()
                    inputStream.close()
                    Log.d("THREADDOWNLOAD", "run:线程${threadID}下载结束")
                    temp.delete()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getFileName(path: String): String {
        val result = path.split("/")
        return cacheDir.absolutePath + "/" + result[result.size - 1]

    }
}
