package builtinfunction

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/builtinfunction
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 13:01
 * @Day:  星期四
 * @Description:
 **************************/
class Run {

    fun toRun() {
        val str = "Barry Allen"
        val i = str.run(::isLength)
            .run(::isTrue)
        println("i = $i")
        val j = str.run {
            println("I can get this , this = $this")
            println("I will return true")
            true
        }
        println("j = $j")

    }

    private fun isLength(str: String): Boolean = str.length > 5
    private fun isTrue(str: Boolean): String = if (str) "Right" else "false"
}

fun main() {
    val run = Run()
    run.toRun()
}