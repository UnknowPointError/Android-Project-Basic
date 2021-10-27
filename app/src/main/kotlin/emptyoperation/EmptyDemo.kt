package emptyoperation

import java.util.*

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/emptyoperation
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/27 15:15
 * @Day:  星期三
 * @Description:
 **************************/
class EmptyDemo {

    fun printString(name: String?) {
        var getName = name?.let {
            if (name.isBlank()) {
                "Default"
            } else {
                "[$it]"
            }
        }
        println("name : $getName")
        name ?: return // 空合并操作符
        var mname =
            name!!.replaceFirstChar {
                if (it.isLowerCase()) {
                    println(it)
                    it.titlecase(Locale.getDefault())
                } else it.toString()
            }
        println("mname : $mname")
    }
}

fun main() {
    val empty = EmptyDemo()
    empty.printString(null)
}