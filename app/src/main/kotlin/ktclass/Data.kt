package ktclass

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/ktclass
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/30 18:54
 * @Day:  星期六
 * @Description:
 **************************/
data class Data(val name: String, val age: Int) {

    fun runData() {
        println("name = $name,age $age")
    }
}

fun main() {
    val data = Data("Barry", 17)
    val (name, age) = Data("Allen", 17)
    data.runData()
    println(data.toString())
    println("name = $name,age = $age")

}