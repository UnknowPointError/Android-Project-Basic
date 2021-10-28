package collections

import android.annotation.SuppressLint

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/collections
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 15:08
 * @Day:  星期四
 * @Description:
 **************************/
class Set {

    fun runSet(set: kotlin.collections.Set<String>) {
        // println("set 0 = ${set[0]}") Error,set can't use set[]
        println("set = $set")
        println("setElement 0 is ${set.elementAt(0)}")
        println("setElement 2 is ${set.elementAt(2)}")
        println("setElement 5 is ${set.elementAtOrElse(5) { "越界了" }}")
        println("setElement 5 is ${set.elementAtOrNull(5)}")
        println("setElement 5 is ${set.elementAtOrNull(5) ?: "越界了"}")
//        set.add  Error,set can't add
    }

    @SuppressLint("NewApi")
    fun mutable(set: MutableSet<String>) {
        println("set = $set")
        set += "Json"
        set -= "Despair"
        set.removeIf { it.contains("n") }
        println("set = $set")
    }
}

fun main() {
    val s = Set()
    val set: kotlin.collections.Set<String> = setOf("Barry", "Allen", "Despair", "Wake")
    val set2: MutableSet<String> = mutableSetOf("Barry", "Allen", "Despair", "Wake")
    s.runSet(set)
    s.mutable(set2)
}