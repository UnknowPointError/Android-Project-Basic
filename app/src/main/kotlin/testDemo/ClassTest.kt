package testDemo

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/testdemo
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/31 21:42
 * @Day:  星期日
 * @Description:
 **************************/
open class ClassTest {
    init {
        println("主构造方法初始化执行")
    }

    open fun toPrint() {
        println("1111")
    }

    fun testa() {
        println(mutableListOf(1, 2, 3))
        val li: MutableList<Any> = mutableListOf()
        li.add(1)
        li.add(2)
        li.add(3)
        println(li)
    }
}

fun main() {
    ClassTest()
    val ba: Int by lazy { 5 }
    val i = object : ClassTest() {
        override fun toPrint() {
            println("999")
        }
    }.toPrint()

    val lista = listOf(1, 2, 3, 4)
    val listb = listOf("a", "b", "c", "d")
    println(lista.map { listOf(it + 1) })
    println(lista.flatMap { listOf(it) })
    ClassTest().testa()
    println("content://cn.example.androidProject.provider/book/\$it")
}