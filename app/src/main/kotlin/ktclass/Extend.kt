package ktclass

import java.io.File

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/ktclass
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/30 14:44
 * @Day:  星期六
 * @Description:
 **************************/
class Extend : A() {

    override fun toPrint(str: String): String {
        super.toPrint("Barry")
        println("子类使用 str = $str")
        return "子类返回 str"
    }
}

open class A {

    open fun toPrint(str : String) : String {
        println("父类使用 str = $str")
        return "父类返回 str"
    }
}
fun main() {
    val ex: A = Extend()
    println(ex.toPrint("Barry"))
    println(ex is A)
    println(ex is Extend)
    println(ex is File)
    if (ex is Extend){
        ex as Extend
        ex.toPrint("Allen")
    }
}