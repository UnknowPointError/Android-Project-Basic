package cn.example.androidProject.broadcast

import android.app.Activity


/*************************
 * @ClassName: LoginActivityCollector.kt
 * @Dir_Path: cn\example\androidProject\broadcast\LoginActivityCollector.kt
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/3 22:29 周三
 * @Description:
 **************************/
object LoginActivityCollector {

    private val activities = ArrayList<Activity>()


    fun addActivity(activity: Activity) = activities.add(activity)

    fun removeActivity(activity: Activity) = activities.remove(activity)

    fun getTopActivity() = activities[activities.size - 1]

    fun finishAll() {
        for (activity in activities)
            if (!activity.isFinishing) activity.finish()
        activities.clear()
    }
}