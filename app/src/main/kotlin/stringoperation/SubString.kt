package stringoperation

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/stringoperation
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/27 15:55
 * @Day:  星期三
 * @Description:
 **************************/
class SubString {

    fun toSubString(index: Int) {
//        substring(0,index) == substring(0 until index)
        val str1 = INFO.substring(0, index)
        val str2 = INFO.substring(0 until index)
        println("str1 : $str1")
        println("str2 : $str2")
    }
}

private const val INFO = "Barry is loser"

fun main() {
    val sub = SubString()
    val indexof = INFO.indexOf('i')
    sub.toSubString(indexof)
}