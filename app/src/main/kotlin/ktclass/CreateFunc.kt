package ktclass

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/ktclass
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/2 14:12
 * @Day:  星期二
 * @Description:
 **************************/
class CreateFunc<T>(val value: T) {
    fun toCreate() {
        val i = create {
            "Barry"
            250
            '牛'
            66.6f
            true
        }.observer {
            println(this)
        }
        println(i)
    }

    private inline fun <OUTPUT> create(block: () -> OUTPUT) = CreateFunc(block())
    private inline fun <INPUT> CreateFunc<INPUT>.observer(block: INPUT.() -> Unit) = block(value)
}

fun main() {
    CreateFunc(1).toCreate()
}