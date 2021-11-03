package cn.example.androidproject.broadcast

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cn.example.androidproject.databinding.LoginofflineActivityBinding

class LoginOffLineActivity : AppCompatActivity() {


    private val mBinding by lazy { LoginofflineActivityBinding.inflate(layoutInflater) }
    private val receiver by lazy { LoginForceOffLine() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        LoginActivityCollector.addActivity(this)
        mBinding.offLine.setOnClickListener {
            val intent = Intent("OFFLINE")
            intent.setPackage(packageName) // Error,I don't know why?
            sendBroadcast(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        Log.e("eee","onResume")
        intentFilter.addAction("OFFLINE")
        registerReceiver(receiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        Log.e("eee","onPause")
        unregisterReceiver(receiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        LoginActivityCollector.removeActivity(this)
    }
}

