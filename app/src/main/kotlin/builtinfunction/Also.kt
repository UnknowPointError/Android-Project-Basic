package builtinfunction

import cn.example.androidProject.util.Util.typename

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/builtinfunction
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 13:34
 * @Day:  星期四
 * @Description:
 **************************/
class Also {

    fun runAlso() {
        val str = "Barry"
        val i = str.also {
            println("(1)I can do may things")
            println("(2)it = $it")
            println("(3)this = also!!!, this.typename = ${this.typename()}")
            println("(4)I can't return the value of the last line")
            true
        }
        println("i = $i")
    }
}

fun main() {
    val a = Also()
    a.runAlso()
}