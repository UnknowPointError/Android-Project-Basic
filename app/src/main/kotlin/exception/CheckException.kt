package exception

import java.lang.Exception

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/exception
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/27 15:43
 * @Day:  星期三
 * @Description:
 **************************/
class CheckException : Exception("Error!!!") {
}

fun main() {
    try {
        var info: String? = null
        val value: Boolean = true
        check(value)
        checkNotNull(info)
//        requireNotNull(info)
        info ?: throw CheckException()
        println(info!!.length)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
