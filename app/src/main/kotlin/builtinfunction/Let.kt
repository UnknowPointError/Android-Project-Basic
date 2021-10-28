package builtinfunction

import cn.example.androidproject.Util.typename

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/builtinfunction
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 13:22
 * @Day:  星期四
 * @Description:
 **************************/
class Let {
    fun runLet() {
        val str = "Barry"
        val i = str.let {
            println("(1)I can do many things")
            println("(2)I get the str is $it")
        }.let {
            println("(3)I can do many things")
            println("(4)I can do many things")
        }.let {
            println("(5)I will return a Boolean type")
            true
        }
        println("(7)i = $i\ti type is ${i.typename()}")
    }
}

fun main() {
    val l = Let()
    l.runLet()
}