package cn.example.androidProject.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import cn.example.androidProject.MainActivity
import cn.example.androidProject.R

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

    override fun onCreate() {
        super.onCreate()
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("my_service",
                "前台service通知",
                NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this, MainActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val notification = NotificationCompat.Builder(this, "my_service")
            .setContentTitle("Download Title")
            .setContentText("Download Text")
            .setSmallIcon(R.drawable.apple_pic)
            .setContentIntent(pi)
            .setAutoCancel(true)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.watermelon_pic))
            .build()
        startForeground(1, notification)
    }
}