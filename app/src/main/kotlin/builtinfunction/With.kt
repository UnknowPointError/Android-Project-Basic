package builtinfunction

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/builtinfunction
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 13:29
 * @Day:  星期四
 * @Description:
 **************************/
class With {

    fun runWith() {
        val str = "Barry"
        println(with(str, {
            println("lambda Run,I will return the value of the last line")
            this

        }))
    }
}

fun main() {
    val w = With()
    w.runWith()
}