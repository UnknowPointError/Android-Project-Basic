package anonymous

import cn.example.androidProject.util.Util.typename

/*************************
 * @Name: Android Project
 * @Ukkkser: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/27 9:04
 * @Day:  星期三
 * @Description:
 **************************/
//    it指向 仅限于只有一种数据类型

class AnonymousFunc(val methodAction: () -> String) {
    val methodNum: (Int, Int, Int) -> String = { num1, num2, num3 ->
        "$num1 $num2 $num3"
    }

    val methodNumbers = { n1: Double, n2: Float, n3: Int ->
        "$n1 $n2 $n3"
    }

    init {
        println("主构造函数初始化执行")
    }

    fun getClassName(): String = this.toString()
}


fun main() {
    val len = "Barry".count {
        println("it = $it")
        it == 'r'
    }
    println("len = $len")
    val anon = AnonymousFunc() {
        "return String"
    }
    println(anon.methodAction())
    println("className = ${anon.getClassName()}")
    println(anon.methodNum(1, 2, 3))
    println(anon.typename())
    println(anon.methodAction.invoke())
//    invoke Equivalent to ()
    println(anon.methodNumbers(5.5, 2.0f, 1)) // 匿名函数自动推断类型
}

