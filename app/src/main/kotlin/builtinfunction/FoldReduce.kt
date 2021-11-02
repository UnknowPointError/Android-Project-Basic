package builtinfunction

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/builtinfunction
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/1 12:54
 * @Day:  星期一
 * @Description:
 **************************/
class FoldReduce {
    /* 1.fold:将集合中的元素依次冒泡组合,最终得到一个结果
    * 第一次执行时,由初始值10作为参数a,由集合中第0个元素作为参数b
    * 第二次执行时,第一次执行的返回值作为参数a,由集合中第1个元素作为参数b
    * 依次类推...
    * 最终将结果返回*/
    fun runFold() {
        val arr = arrayOf(1, 2, 3, 4)
        val sum = arr.fold(10, { a, b -> a + b })
        println("sum = $sum")
    }

    // reduce:与fold类似,区别是reduce没有初始值
    fun runReduce() {
        val arr = arrayOf(1, 2, 3, 4)
        val sum = arr.reduce { a, b -> a + b }
        println("sum = $sum")
    }
}

fun main() = FoldReduce().run {
    runFold()
    runReduce()
}