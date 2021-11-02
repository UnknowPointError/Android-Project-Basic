package cn.example.androidproject.broadcast

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cn.example.androidproject.databinding.LoginofflineActivityBinding

class LoginOffLineActivity : AppCompatActivity() {


    private val mBinding by lazy { LoginofflineActivityBinding.inflate(layoutInflater) }
    private lateinit var receiver: LoginForceOffLine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        LoginActivityCollector.addActivity(this)
        mBinding.offLine.setOnClickListener {
            val intent = Intent("OFFLINE")
            intent.setPackage(packageName)
            Log.e("eee", "$packageName")
            sendBroadcast(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("OFFLINE")
        receiver = LoginForceOffLine()
        registerReceiver(receiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        LoginActivityCollector.removeActivity(this)
    }
}

