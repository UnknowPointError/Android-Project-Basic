package hightorderfunc

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/hightorderfunc
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/27 12:20
 * @Day:  星期三
 * @Description:
 **************************/


const val USER_NAME_SAVE_DB = "Barry"
const val USER_PWD_SAVE_DB = "123456"

class HighFunc(username: String, userpwd: String, responseResult: (String, Int) -> Unit) {
    init {
        if (username == null || userpwd == null) {
            TODO("用户名或密码为null") // 出现问题，终止程序。
        }

        if (username.length > 3 && userpwd.length > 3) {
            if (webServiceLoginAPI(username, userpwd)) {
                responseResult("login success", 200)
            } else {
                responseResult("login error", 404)
            }
        }
    }


    private fun webServiceLoginAPI(name: String, pwd: String) =
        name == USER_NAME_SAVE_DB && pwd == USER_PWD_SAVE_DB
}

fun functionreference(msg: String, code: Int) {
    println("最终的登录结果msg:$msg code:$code")
}

fun main() {
    HighFunc("Barry", "123456") { str1, int1 ->
        println("response : $str1 requestcode : $int1")
    }
    HighFunc("Barry", "1234568", ::functionreference)
}
