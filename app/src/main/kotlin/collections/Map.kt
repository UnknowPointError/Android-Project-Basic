package collections

import android.annotation.SuppressLint
import kotlin.collections.Map

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/collections
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 15:39
 * @Day:  星期四
 * @Description:
 **************************/
class Map {

    fun runMap(map: Map<String, String>) {
        println("map[\"Barry\"] = ${map["Barry"]} ") // 背后对[] 运算符重载
        println("map[\"Sun\"] = ${map["Sun"]} ")
        println("map[\"Light\"] = ${map["Light"]} ") // 找不到键值则返回null
        println("map[\"Barry\"].getOrDefault = ${map.getOrDefault("Barry", -1)} ")
        println("map[\"Sun\"].getOrDefault = ${map.getOrDefault("Sun", -1)} ")
        println("map[\"Light\"].getOrDefault = ${map.getOrDefault("Light", -1)} ")
        println("map[\"Barry\"].getOrElse = ${map.getOrElse("Barry") { -1 }} ")
        println("map[\"Sun\"].getOrElse = ${map.getOrElse("Sun") { -1 }} ")
        println("map[\"Light\"].getOrElse = ${map.getOrElse("Light") { -1 }} ")
    }

    @SuppressLint("NewApi")
    fun forEach(map: Map<String, String>) {
        println("---------------------")
        map.apply {
            forEach {
                println("keys = ${it.key} , values = ${it.value}")
            }
            forEach { (keys, values) ->
                println("keys = $keys , values = $values")
            }
            forEach { keys, values ->
                println("keys = $keys , values = $values")
            }
            for (item in map) {
                println("keys = ${item.key} , values = ${item.value}")
            }
        }
    }

    fun mutable(map: MutableMap<String, String>) {
        println("---------------------")
        map += "AAA" to "BBB"
        map -= "Ground"
            map["CCC"] = "DDD"
        map.put("EEE", "FFF")   //put 和 [] 等价
        map.getOrPut("GGG") { "HHH" }
        println("Map = $map")
        map.getOrPut("GGG") { "KKK  " }
        println("Map = $map")
    }
}

fun main() {
    val mMap1: Map<String, String> = mapOf("Barry" to "Allen", "Sun" to "Moon")
    val mMap2: Map<String, String> = mapOf(Pair("Black", "White"), Pair("Ground", "Sky"))
    val mMap3: MutableMap<String, String> =
        mutableMapOf(Pair("Black", "White"), Pair("Ground", "Sky"))
    val map = Map()
    map.runMap(mMap1)
    map.forEach(mMap2)
    map.mutable(mMap3)
}