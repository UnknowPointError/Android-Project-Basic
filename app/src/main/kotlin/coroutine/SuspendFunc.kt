package coroutine

import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/coroutine
 * @Author: BarryAllen
 * @Time: 2021/11/12 13:30 星期五
 * TODO:
 **************************/
class SuspendFunc {
    suspend fun doThing() {
        coroutineScope {
            delayTime(1000)
        }
    }

    suspend fun delayTime(i: Int) {
        val i = suspendCoroutine<String> {
            var j = 0
            for (i in 0..1000);
        }
        println(i)
    }

    fun normalFunc() {
        println("An ordinary function")
    }
}

fun main() {
    val sus = SuspendFunc()
    runBlocking {
        sus.normalFunc()
        println("asd")
        sus.delayTime(1)
    }

}
