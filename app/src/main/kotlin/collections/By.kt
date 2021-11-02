package collections

import kotlin.reflect.KProperty

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/collections
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/31 21:57
 * @Day:  星期日
 * @Description:
 **************************/
open class By {

    var propValue: Any? = null
    operator fun getValue(myclass: MyClass?, property: KProperty<*>): Any? {
        return propValue
    }

    operator fun setValue(myClass: MyClass, property: KProperty<*>, value: Any?) {
        propValue = value
    }
}

class MyClass {
    var p by By()
}


fun main() {
    val i = MyClass()
    i.p = 5
    println("i = ${i.p}")
}