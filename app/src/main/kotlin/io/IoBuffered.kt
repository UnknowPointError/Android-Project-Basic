package io

import cn.example.androidProject.Util.multiplication
import java.io.*

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/io
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/3 16:11
 * @Day:  星期三
 * @Description:
 **************************/
class IoBuffered {

    fun bufferedWrite() {
        val start = System.currentTimeMillis()
        val str = "BarryAllen".multiplication(9999)
        val file = "app\\src\\main\\kotlin\\io\\UnKnow.txt"
        val writer = BufferedWriter(FileWriter(file))
        writer.write(str)
        writer.flush()
        writer.close()
        val end = System.currentTimeMillis()
        println("一共执行了: ${end - start} 毫秒")
    }

    fun bufferedRead() {
        val start = System.currentTimeMillis()
        val file = "app\\src\\main\\kotlin\\io\\UnKnow.txt"
        val reader = BufferedReader(FileReader(file))
//        println(reader.readLines())
        reader.forEachLine { println(it) }
        reader.close()
        val end = System.currentTimeMillis()
        println("一共执行了: ${end - start} 毫秒")

    }
}

fun main() {
    val io = IoBuffered()
    io.bufferedWrite()
    io.bufferedRead()
}
