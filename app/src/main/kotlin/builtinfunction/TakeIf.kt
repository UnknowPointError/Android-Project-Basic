package builtinfunction

import java.lang.Error

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/builtinfunction
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 13:52
 * @Day:  星期四
 * @Description:
 **************************/
class TakeIf {

    fun runTakeIf() {
        val result = checkPermissionAction("Barry", "123")
        val result2 = checkPermissionAction2("Barry", "123")
        println("result = $result")
        println("result2 = $result2")
    }

    private fun checkPermissionAction2(name: String, pwd: String): Any {
        return name.takeUnless { permissionSystem(name, pwd) } ?: "$name$pwd"
    }

    private fun checkPermissionAction(name: String, pwd: String): String {
        return name.takeIf { permissionSystem(name, pwd) } ?: "Error"
    }

    private fun permissionSystem(name: String, pwd: String): Boolean {
        return name == "Barry" && pwd == "123"
    }
}

fun main() {
    val tif = TakeIf()
    tif.runTakeIf()
}