package collections

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/collections
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 15:23
 * @Day:  星期四
 * @Description:
 **************************/
class Array {

    fun runArray(intArray: IntArray) {
        println("intArray[0] = ${intArray[0]}")
        println("intArray[0] = ${intArray.elementAt(0)}")
        println("intArray[6] = ${intArray.elementAtOrElse(6) { -1 }}")
        println("intArray[6] = ${intArray.elementAtOrNull(6)}")
        println("intArray[6] = ${intArray.elementAtOrNull(6) ?: "Error"}")
    }
}

fun main() {
    val arr = Array()
    val intArray = intArrayOf(1, 2, 3, 4, 5, 6)
    arr.runArray(intArray)
}