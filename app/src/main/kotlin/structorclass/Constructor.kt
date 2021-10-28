package structorclass

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/structorclass
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 16:58
 * @Day:  星期四
 * @Description:
 **************************/
class Constructor(name: String) { // 主钩造

    /*init {
        println("name = $name")
    }*/

    // 次构造函数必须要调用主钩造函数，否则编译不通过，目的是为了统一管理，为了更好的初始化设计的
    constructor(name: String, sex: Char) : this(name) {
        println("name = $name sex = $sex")
    }

    constructor(name: String, sex: Char, age: Int) : this(name) {
        println("name = $name sex = $sex age = $age")
    }

}

fun main() {
    Constructor("Barry")
    Constructor("Barry", '男')
    Constructor("Allen", '女', 0)
}