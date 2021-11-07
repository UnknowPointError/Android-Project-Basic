package cn.example.androidProject.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.TextView
import cn.example.androidProject.databinding.ServiceActivityBinding

class ServiceActivity : AppCompatActivity() {
    companion object {
        var text: String = "未绑定服务"
    }

    lateinit var downloadBinder: MyService.DownloadBinder
    private val mBinding by lazy { ServiceActivityBinding.inflate(layoutInflater) }
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName?, service: IBinder?) {
            text = "已绑定服务"
            Log.e("aaa", "执行")
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
            mBinding.serviceTextView.text = text
        }

        override fun onServiceDisconnected(componentName: ComponentName?) {
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initComponent()
    }

    private fun initComponent() {
        mBinding.apply {
            bindService.setOnClickListener {
                val intent = Intent(this@ServiceActivity, MyService::class.java)
                bindService(intent, connection, Context.BIND_AUTO_CREATE)
            }
            unBindService.setOnClickListener {
                unbindService(connection)
                mBinding.serviceTextView.text = "取消绑定服务"
            }
        }
    }
}