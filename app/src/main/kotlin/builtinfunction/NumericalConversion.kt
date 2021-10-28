package builtinfunction

import kotlin.math.roundToInt

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/builtinfunction
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 12:50
 * @Day:  星期四
 * @Description:
 **************************/
class NumericalConversion {

    fun cast() {
        val num1 = 65.5645756
        // roundToInt四舍五入更加精确
        println("num1 toInt = ${num1.toInt()}")
        println("num1 roundToInt = ${num1.roundToInt()}")
        println("format num = ${"%.3f".format(num1)}")
    }
}

fun main() {
    val ncs = NumericalConversion()
    ncs.cast()
}