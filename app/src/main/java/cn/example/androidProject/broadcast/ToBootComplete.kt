package cn.example.androidProject.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import cn.example.androidProject.MyApplication
import cn.example.androidProject.util.Util.showToast


/*************************
 * @ClassName: ToBootComplete.kt
 * @Dir_Path: cn\example\androidProject\broadcast\ToBootComplete.kt
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/3 22:29 周三
 * @Description:
 **************************/
class ToBootComplete : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        MyApplication.context = context
        context.showToast("Boot Complete")
    }
}