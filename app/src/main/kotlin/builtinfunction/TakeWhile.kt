package builtinfunction

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/builtinfunction
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/1 13:13
 * @Day:  星期一
 * @Description:
 **************************/
class TakeWhile {
    /* 1.takeWhile:遍历list中的元素,将符合要求的元素添加到新集合中
    * 注意:一旦遇到不符合要求的,直接终止 */
    fun runTakeWhile() {
        val arr = arrayOf(1, 2, 3, 4, 5, 0, 9)
        val result = arr.takeWhile { it > 0 }
        println("result = $result")
    }
}

fun main() = TakeWhile().runTakeWhile()