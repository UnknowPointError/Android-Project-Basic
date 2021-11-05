package io

import java.io.*

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/io
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/3 15:01
 * @Day:  星期三
 * @Description:
 **************************/
class IoStream {

    fun write() {
        val data: ByteArray = byteArrayOf(1, 2, 4, 6, 8, 10)
        val file = File("app\\src\\main\\kotlin\\io\\Barry.txt")
        val write = FileOutputStream(file)
        write.write(data)
        write.close()
    }

    fun read() {
        var data = ByteArray(1000)
        val file = File("app\\src\\main\\kotlin\\io\\Barry.txt")
        val read = FileInputStream(file)
        val n = read.read(data)
        read.close()
        println("读取了 $n 个字节")
    }

    fun writeString(str: String) {
        val data = str.toByteArray()
        val file = File("app\\src\\main\\kotlin\\io\\Allen.txt")
        val write = FileOutputStream(file)
        write.write(data)
        write.close()
    }

    fun readString() {
        var data = ByteArray(1000)
        val file = File("app\\src\\main\\kotlin\\io\\Allen.txt")
        val read = FileInputStream(file)
        val n = read.read(data)
        read.close()
        val content = String(data,0,n)
        println("读取了 $n 个字节")
        println("内容是 $content")
    }
}


fun main() {
    val io = IoStream()
    io.write()
    io.writeString("Barry")
    io.read()
    io.readString()
}