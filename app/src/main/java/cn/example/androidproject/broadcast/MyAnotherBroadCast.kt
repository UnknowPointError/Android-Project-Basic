package cn.example.androidproject.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import cn.example.androidproject.Util.showToasts

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidproject/broadcast
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/2 22:43
 * @Day:  星期二
 * @Description:
 **************************/
class MyAnotherBroadCast : BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent?) {
        context.showToasts("接收自定义其他广播")
        abortBroadcast()
    }
}