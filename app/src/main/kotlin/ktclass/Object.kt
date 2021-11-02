package ktclass

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/ktclass
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/30 15
 * :31
 * @Day:  星期六
 * @Description:
 **************************/
class ObjectExpression {

    var p1 = object : Runnable {
        override fun run() {
            println("run")
        }
    }.run()
}

fun main() {
//    ObjectExpression().p1.run()
    ObjectExpression()
}