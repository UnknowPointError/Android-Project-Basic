@file:JvmName("Stu")
package ktclass

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/ktclass
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/2 13:35
 * @Day:  ζζδΊ
 * @Description:
 **************************/
class Annotation {
    @JvmField
    val names = listOf("Barry", "Allen", "UnKnow")
}

fun main() {
    val ano = Annotation()
    println("names = ${ano.names}")
}