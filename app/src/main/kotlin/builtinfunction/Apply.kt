package builtinfunction

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/builtinfunction
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 13:44
 * @Day:  星期四
 * @Description:
 **************************/
class Apply {

    fun runApply() {
        val str = "Barry"
        val i = str.apply {
            println("(1)I use this, this = $this")
            println("(2)I can't return the value of last line")
            true
        }
        println("i = $i")
    }
}

fun main() {
    val al = Apply()
    al.runApply()
}