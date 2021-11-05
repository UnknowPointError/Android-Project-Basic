package cn.example.androidProject.recyclerTalk

/*************************
 * @Name: Android Project
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/26 19:27
 * @Day:  星期二
 * @Description:
 **************************/
class Msg(val content: String, val type: Int) {
    companion object {
        const val TYPE_RECEIVED = 0
        const val TYPE_SEND = 1
    }
}