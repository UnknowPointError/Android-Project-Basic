package stringoperation

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/27 16:01
 * @Day:  星期三
 * @Description:
 **************************/
class SplitString {

    fun toSplitString(): List<String> {
        // list 根据类型自动推断成 list == List<String   >
        val list = INFO.split(',')
        return list
    }
}

private const val INFO = "Barry,is,a,loser"

fun main() {
    val spl = SplitString()
    val value = spl.toSplitString()
    val (v1, v2, v3, v4) = value
    println("value : $value")
    println("value : $v1 $v2 $v3 $v4")
    println("value : ${value[3]}")
}