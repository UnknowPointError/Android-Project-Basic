package builtinfunction

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/builtinfunction
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/1 13:16
 * @Day:  星期一
 * @Description:
 **************************/
class ZipFunc {


    // 合并集合后创建新的集合并返回
    fun runZip() {
        val arr = arrayOf(1, 2, 3, 6, 0, 7, 8, 9)
        val arr2 = arrayOf(2, 3, 4, "123", "123222", "barry")
        val result = arr.zip(arr2)
        println(result)
    }
}

fun main() = ZipFunc().runZip()