package cn.example.androidProject.jetpack

import android.util.Log
import androidx.lifecycle.Lifecycle.*
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/*************************
* @ProjectName: Android Project
* @Dir_Path: app/src/main/java/cn/example/androidProject/jetpack
* @User: Lzp
* @Author: BarryAllen
* @Time: 2021/11/11 9:58 星期四
* @Description:
**************************/
class MyObserver : LifecycleObserver {

    @OnLifecycleEvent(Event.ON_START)
    fun activityStart() {
        Log.e("MyObserver", "activity Start")
    }

    @OnLifecycleEvent(Event.ON_STOP)
    fun activityStop() {
        Log.e("MyObserver", "activity Stop")
    }

}