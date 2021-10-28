package collections

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/list
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 14:05
 * @Day:  星期四
 * @Description:
 **************************/
class ListDemo1 {

    fun runList(list: List<String>) {

        println("list = $list")
        println("list[] == list.get")
        println("list.getOrElse = ${list.getOrElse(5) { "越界了" }}")
        println("list.getOrNull = ${list.getOrNull(5)}")
        println("list.getOrNull = ${list.getOrNull(5) ?: "越界了"}")
//        list.add You can't add!!! Because the list is Immutable
//        list.remove You can't remove!!! Because the list is Immutable
//        If you want to implement add and other operations
        val list2: MutableList<String> = list.toMutableList()
        list2.add("PHP")
        list2.remove("C")
        println("list2 = $list2")
        list2.add("PHP")
        println("list2 = $list2")
        println("list2 = ${list2.distinct()}")

    }

    fun forEach(list: List<String>) {
        println("-----------------------")
        for ((i, s) in list.withIndex()) {
            println("($i)s = $s")
        }
        var i = 0
        println("-----------------------")
        list.forEach {
            i++
            println("($i)it = $it")
        }
        println("-----------------------")
        list.forEachIndexed { index, item ->
            println("($index) $item")
        }
    }

    fun filterList(list: List<String>) {
        var (v1, v2, v3) = list // 解构语法
        v1 = "JavaScript"
        println("v1 = $v1 v2 = $v2 v3 = $v3")
        val (_, _, kt) = list
        println("kt = $kt") // 过滤解构赋值，屏蔽接受值：_,反编译后可发现无创建过滤值
        // 减少性能开销
    }
}


fun main() {
    val list = ListDemo1()
    val listData = listOf("Python", "Java", "Kotlin", "C++", "C")
    list.runList(listData)
    list.forEach(listData)
    list.filterList(listData)
}
