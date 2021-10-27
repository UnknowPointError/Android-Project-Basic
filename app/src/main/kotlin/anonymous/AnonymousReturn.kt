package anonymous

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/anonymous
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/27 14:41
 * @Day:  星期三
 * @Description:
 **************************/
class AnonymousReturn {
    fun show(info: String): (String, Int) -> String {
        println("show Function")
        return { name: String, value: Int ->
            "$name and $value"
        }
    }
}


fun main() {
    val anon = AnonymousReturn()
    println(anon.show("Hello")("M", 10))
}