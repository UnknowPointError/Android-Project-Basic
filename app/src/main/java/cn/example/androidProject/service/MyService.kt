package cn.example.androidProject.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import cn.example.androidProject.Util.showToasts

class MyService : Service() {

    class DownloadBinder : Binder() {

        fun startDownload() {
            ServiceActivity.text += "\nStart Download\n"
        }

        fun getProgress(): Int {
            ServiceActivity.text += "Get Progress"
            return 0
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return DownloadBinder()
    }
}