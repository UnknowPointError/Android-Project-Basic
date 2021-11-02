package builtinfunction

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/builtinfunction
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/1 11:50
 * @Day:  星期一
 * @Description:
 **************************/
class MapFunc {
    // 1.map:将List中每个元素转换成新的元素,并添加到一个新的List中,最后将新List返回
    fun runMap() {
        val arr = listOf("Barry", "Allen", "Never")
        val arr1 = arrayOf(1, 2, 3)
        val list = arr.map { i: String -> i + "Failed" }
        arr1.map { i: Int -> i * 10 }.forEach { println(it) }
        println("arr = $arr, list = $list")
    }
    // 2.flatMap:将数组中全部元素按顺序组成一个list
    // 注意:lambda表达式中的参数类型可以不写.如:List<String>和IntRange

    fun runFlatMap() {
        val intList = listOf(1..5, 1..10)
        val strList = listOf(listOf("a", "b"), listOf("d", "c"))
        val fIntList = intList.flatMap { it.asIterable() }
        val fStrList = strList.flatMap { it.asIterable() }
        println(fIntList)
        println(fStrList)
        println(strList)
    }


    fun runFunc() {
        val arr = arrayOf("Barry", "Allen", "UnKnow")
        val age = arrayOf("16", "17", "18")
        arr.zip(age).toMap().map { "name is ${it.key} value is ${it.value}" }.forEach { println(it) }
    }
}

fun main() = MapFunc().run {
    runMap()
    runFlatMap()
    runFunc()
}

