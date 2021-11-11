package cn.example.androidProject.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import cn.example.androidProject.MyApplication
import cn.example.androidProject.util.Util.showToast

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidProject/broadcast
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/2 22:17
 * @Day:  星期二
 * @Description:
 **************************/
class MyBroadCast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        showToast("接受自定义广播")
    }
}