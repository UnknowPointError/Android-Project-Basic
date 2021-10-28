package collections

import android.annotation.SuppressLint

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/list
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 14:12
 * @Day:  星期四
 * @Description:
 **************************/
class Mutable {

    @SuppressLint("NewApi")
    fun runMutable() {
        val list: MutableList<String> = mutableListOf("Python", "Java", "Kotlin", "C++", "C")
        println("(1)list = $list")
        list.add("Go")
        list.add("Lua")
        list.remove("C")
        println("(2)list = $list")
        list += "Html"
        list -= "C++"
        println("(3)list = $list")
        list.removeIf { it.contains("o") }
        println(list)
    }
}

fun main() {
    val mt = Mutable()
    mt.runMutable()
}