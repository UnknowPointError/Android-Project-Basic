package cn.example.androidproject

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import cn.example.androidproject.basic.BasicActivity
import cn.example.androidproject.Util.showToasts
import cn.example.androidproject.broadcast.LoginActivity
import cn.example.androidproject.databinding.ActivityMainBinding
import cn.example.androidproject.fragment.NewsActivity
import cn.example.androidproject.listview.ListActivity
import cn.example.androidproject.recyclertalk.TalkActivity
import cn.example.androidproject.recyclerview.RecyclerActivity
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val actionTimeTick = "android.intent.action.TIME_TICK"
    private val actionFly = "android.intent.action.AIRPLANE_MODE"
    private val main = this
    private val manager: ActivityManager by lazy {
        getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    }
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initComponent()
        timeChangeNotice()
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
                sendBroadCast.id -> {
                    val intent = Intent("MyBroadCastReceive")
                    intent.setPackage(packageName)
                    sendOrderedBroadcast(intent, null)
                }
                loginBroadcast.id -> startActivity<LoginActivity> { }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }

    private fun timeChangeNotice() {
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        intentFilter.addAction("android.intent.action.AIRPLANE_MODE")
        registerReceiver(timeChangeReceiver, intentFilter)
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
            sendBroadCast.setOnClickListener(main)
            loginBroadcast.setOnClickListener(main)
        }
    }

    private inline fun <reified T> startActivity(block: Intent.() -> Unit) {
        val intent = Intent(this, T::class.java)
        intent.block()
        this.startActivity(intent)
    }

    private fun checkMemory(text: String = "检查内存") {
        val info = ActivityManager.MemoryInfo()
        manager.getMemoryInfo(info)
        val longMemory: Long = info.availMem / 1024 / 1024
        val totalMemory: Long = info.totalMem / 1024 / 1024
        main.showToasts("【$text】\n可使用的内存为：$longMemory MB\n总内存为：$totalMemory MB")
    }
}

