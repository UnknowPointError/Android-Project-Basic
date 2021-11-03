package cn.example.androidproject.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import cn.example.androidproject.MainActivity

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidproject/broadcast
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/2 23:47
 * @Day:  星期二
 * @Description:
 **************************/

class LoginForceOffLine : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        AlertDialog.Builder(context).apply {
            setTitle("Warning")
            setMessage("You are forced to be offline.Please try to login again.")
            setCancelable(false)
            setPositiveButton("OK") { _, _ ->
                LoginActivityCollector.finishAll()
                context.startActivity(Intent(context,
                    LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            }
            show()
        }
    }
}
