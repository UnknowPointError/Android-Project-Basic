package cn.example.androidProject

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidProject/util
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/11 15:42 星期四
 * @Description:
 **************************/
class MyApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}