package ktclass

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/class
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/30 14:35
 * @Day:  星期六
 * @Description:
 **************************/
class Lazy {

    private lateinit var lazy1: String
    private val lazy2: String by lazy { "Hello,World" }
    fun runLazy() {
        if (!::lazy1.isInitialized)
            lazy1 = "Bye"
        println("lazy1 = $lazy1")
        println("lazy2 = $lazy2")
    }
}

fun main() {
    val l = Lazy()
    l.runLazy()
}