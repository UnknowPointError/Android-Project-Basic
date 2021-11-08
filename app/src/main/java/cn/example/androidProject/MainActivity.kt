package cn.example.androidProject

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import cn.example.androidProject.http.HttpActivity
import cn.example.androidProject.basic.BasicActivity
import cn.example.androidProject.Util.showToasts
import cn.example.androidProject.broadcast.LoginActivity
import cn.example.androidProject.cameraAlbum.CameraAlbumActivity
import cn.example.androidProject.contentProvider.DataBaseProviderActivity
import cn.example.androidProject.contentProvider.getContacts.ContactsActivity
import cn.example.androidProject.databinding.ActivityMainBinding
import cn.example.androidProject.fragment.NewsActivity
import cn.example.androidProject.listView.ListActivity
import cn.example.androidProject.media.MediaActivity
import cn.example.androidProject.notification.NoticeActivity
import cn.example.androidProject.recyclerTalk.TalkActivity
import cn.example.androidProject.recyclerView.RecyclerActivity
import cn.example.androidProject.service.ServiceActivity
import cn.example.androidProject.storage.filePersistence.FileActivity
import cn.example.androidProject.storage.sharedPreferences.SharedPreferencesActivity
import cn.example.androidProject.storage.sqLite.DatabaseActivity
import kotlinx.coroutines.DelicateCoroutinesApi

/*************************
 * @ClassName: MainActivity.kt
 * @Dir_Path: cn\example\androidProject\MainActivity.kt
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/3 22:26 周三
 * @Description:
 **************************/
@DelicateCoroutinesApi
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val actionTimeTick = "android.intent.action.TIME_TICK"
    private val actionFly = "android.intent.action.AIRPLANE_MODE"
    private val main = this
    private val timeChangeReceiver by lazy {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when (intent?.action) {
                    actionFly -> main.showToasts("飞行模式已变化")
                    actionTimeTick -> checkMemory("时间改变")
                }
            }
        }
    }
    private val dynamicBroadcastReceiver by lazy {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when (intent?.action) {
                    "DynamicAction" -> main.showToasts("Okay")
                }
            }
        }
    }
    private val manager by lazy { getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initComponent()
        registerBroadCast()
        initNotice()

    }


    override fun onClick(v: View?) {
        mBinding.apply {
            when (v?.id) {
                basicBtn.id -> startActivity<BasicActivity> { }
                basicListviewBtn.id -> startActivity<ListActivity> {
                    putExtra("listData", "Basic")
                }
                listviewBtn.id -> startActivity<ListActivity> { }
                recyclerViewGrid.id -> startActivity<RecyclerActivity> {
                    putExtra("RecyclerView", "Grid")
                }
                recyclerViewLinear.id -> startActivity<RecyclerActivity> { }
                talk.id -> startActivity<TalkActivity> { }
                news.id -> startActivity<NewsActivity> { }
                checkMemory.id -> checkMemory()
                sendStaticBroadCast.id -> {// 发送自定义静态有序广播
                    val intent = Intent("MyBroadCastReceive")
                    intent.setPackage(packageName)
                    sendOrderedBroadcast(intent, null)
                }
                loginBroadcast.id -> startActivity<LoginActivity> { }
                sendDynamicBroad.id -> { // 发送自定义动态标准广播
                    val intent = Intent("DynamicAction")
                    sendBroadcast(intent)
                }
                fileStorage.id -> startActivity<FileActivity> { }
                sharedPreferences.id -> startActivity<SharedPreferencesActivity> { }
                sqLite.id -> startActivity<DatabaseActivity> { }
                makeCall.id -> makeCall()
                getContacts.id -> startActivity<ContactsActivity> { }
                contentProvider.id -> startActivity<DataBaseProviderActivity> { }
                sendNotice.id -> notice()
                media.id -> startActivity<MediaActivity> { }
                service.id -> startActivity<ServiceActivity> { }
                cameraAlbum.id -> startActivity<CameraAlbumActivity> { }
                http.id -> startActivity<HttpActivity> {  }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
        unregisterReceiver(dynamicBroadcastReceiver)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone()
                } else {
                    main.showToasts("You denied the Permission.")
                }
            }
        }
    }

    private fun makeCall() {
        if (ContextCompat.checkSelfPermission(main,
                android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(main,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                1)
        } else {
            callPhone()
        }
    }

    private fun callPhone() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun registerBroadCast() {
        // timeChangeReceive是系统发出广播我只需接收即可
        val intentFilterTime = IntentFilter()
        val intentFilterDynamic = IntentFilter()
        intentFilterTime.addAction("android.intent.action.TIME_TICK")
        intentFilterTime.addAction("android.intent.action.AIRPLANE_MODE")
        intentFilterDynamic.addAction("DynamicAction")
        registerReceiver(timeChangeReceiver, intentFilterTime)
        registerReceiver(dynamicBroadcastReceiver, intentFilterDynamic)
    }


    private inline fun <reified T> startActivity(block: Intent.() -> Unit) {
        val intent = Intent(this, T::class.java)
        intent.block()
        this.startActivity(intent)
    }

    private fun checkMemory(text: String = "检查内存") {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val info = ActivityManager.MemoryInfo()
        manager.getMemoryInfo(info)
        val longMemory: Long = info.availMem / 1024 / 1024
        val totalMemory: Long = info.totalMem / 1024 / 1024
        main.showToasts("【$text】\n可使用的内存为：$longMemory MB\n总内存为：$totalMemory MB")
    }

    private fun initNotice() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel1 =
                NotificationChannel("1", "Normal", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel1)
            val channel2 =
                NotificationChannel("2", "Important", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel2)
        }

    }

    private fun notice() {
        val pi = PendingIntent.getActivity(this, 0, Intent(this, NoticeActivity::class.java), 0)
        val notification1 =
            NotificationCompat.Builder(this, "1")
                .setContentTitle("This is content title")
                .setStyle(NotificationCompat.BigTextStyle().bigText("""private fun initNotice() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("1", "name", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)

        }"""))
                .setStyle(NotificationCompat.BigPictureStyle()
                    .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.a3)))
                .setSmallIcon(R.drawable.apple_pic)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build()
        val notification2 = NotificationCompat.Builder(this, "2")
            .setContentTitle("This is content title")
            .setContentText("This is content text")
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.banana_pic))
            .setSmallIcon(R.drawable.apple_pic)
            .setContentIntent(pi)
            .setAutoCancel(true)
            .build()
        manager.notify(1, notification1)
        manager.notify(2, notification2)
    }


    private fun initComponent() {
        mBinding.apply {
            basicBtn.setOnClickListener(main)
            basicListviewBtn.setOnClickListener(main)
            listviewBtn.setOnClickListener(main)
            recyclerViewGrid.setOnClickListener(main)
            recyclerViewLinear.setOnClickListener(main)
            talk.setOnClickListener(main)
            news.setOnClickListener(main)
            checkMemory.setOnClickListener(main)
            sendStaticBroadCast.setOnClickListener(main)
            loginBroadcast.setOnClickListener(main)
            sendDynamicBroad.setOnClickListener(main)
            sendDynamicBroad.autoLinkMask
            sendStaticBroadCast.autoLinkMask
            fileStorage.setOnClickListener(main)
            sharedPreferences.setOnClickListener(main)
            sqLite.setOnClickListener(main)
            makeCall.setOnClickListener(main)
            getContacts.setOnClickListener(main)
            contentProvider.setOnClickListener(main)
            sendNotice.setOnClickListener(main)
            media.setOnClickListener(main)
            service.setOnClickListener(main)
            cameraAlbum.setOnClickListener(main)
            http.setOnClickListener(main)
        }
    }
}

