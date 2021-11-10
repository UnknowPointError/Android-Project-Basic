package cn.example.androidProject.util

import java.lang.RuntimeException

object BasicUtil {

    fun <T : Comparable<T>> max(vararg values: T): T {
        (throw RuntimeException("Params can not be empty.")).takeIf { values.isEmpty() }
        var maxValue = values[0]
        for (value in values) {
            if (value > maxValue) {
                maxValue = value
            }
        }
        return maxValue
    }


}