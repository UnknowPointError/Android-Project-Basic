package cn.example.androidproject.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import cn.example.androidproject.Util.showToasts

class ToBootComplete : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.showToasts("Boot Complete")
    }
}