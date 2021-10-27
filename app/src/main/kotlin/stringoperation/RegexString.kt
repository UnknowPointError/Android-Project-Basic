package stringoperation

import java.io.File

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/stringoperation
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/27 16:19
 * @Day:  星期三
 * @Description:
 **************************/
class RegexString {

    fun toRegexString() {
        val r1 = sourecePwd.replace(Regex("[ABCJOK]")) {
            when (it.value) {
                "A" -> "9"
                "B" -> "8"
                "C" -> "7"
                "J" -> "6"
                "O" -> "5"
                "K" -> "4"
                else -> it.value
            }
        }
        println("r1 = $r1")
        val r2 = sourecePwd.replace(Regex("[987654]")) {
            when (it.value) {
                "9" -> "A"
                "8" -> "B"
                "7" -> "C"
                "6" -> "J"
                "5" -> "O"
                "4" -> "K"
                else -> it.value
            }
        }
        println("r2 = $r2")
    }
}

private const val sourecePwd = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

fun main() {
    val regex = RegexString()
    regex.toRegexString()
    val r8 = Regex("[0-9]+")
    println(r8.replace("12XYZ9") { (it.value.toInt() * it.value.toInt()).toString() })

    File("app\\src\\main\\kotlin\\Note.txt").apply {
        setExecutable(true)
        setReadable(true)
        println("----------")
        readLines().forEach { println(it) }
    }
}