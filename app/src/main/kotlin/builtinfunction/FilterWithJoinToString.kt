package builtinfunction

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/builtinfunction
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/1 12:59
 * @Day:  星期一
 * @Description:
 **************************/
class FilterWithJoinToString {

    // 1.filter:将中的元素遍历,把符合要求的元素添加到新的list中,并将新list返回
    fun runFilter() {
        val arr = arrayOf(1, 2, 3, 0, 4)
        val result = arr.filter { it > 0 }
        println("result = $result")
    }
    // 1.joinToString:为集合元素添加分隔符,组成一个新的字符串并返回
    fun runJoinToString() {
        val arr = arrayOf("a", "b", "c", "d")
        val result1 = arr.joinToString { it }
        val result2 = arr.joinToString(separator = "#",
            prefix = "[前缀]",
            postfix = "[后缀]",
            limit = 3,
            truncated = "[省略号]") { it }
        println("result1 = $result1 result2 = $result2")
    }
}

fun main() = FilterWithJoinToString().run {
    runFilter()
    runJoinToString()
}