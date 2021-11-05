package cn.example.androidProject.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import cn.example.androidProject.R
import cn.example.androidProject.databinding.LoginofflineActivityBinding


/*************************
 * @ClassName: LoginOffLineActivity.kt
 * @Dir_Path: cn\example\androidProject\broadcast\LoginOffLineActivity.kt
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/3 22:29 周三
 * @Description:
 **************************/
class LoginOffLineActivity : AppCompatActivity() {

    inner class LoginForceOffLine : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            AlertDialog.Builder(LoginActivityCollector.getTopActivity(),
                R.style.Theme_AppCompat_Dialog)
                .apply {
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

    private val mBinding by lazy { LoginofflineActivityBinding.inflate(layoutInflater) }
    private val receiver by lazy { LoginForceOffLine() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        LoginActivityCollector.addActivity(this)
        mBinding.offLine.setOnClickListener {
            val intent = Intent("OFFLINE")
            sendBroadcast(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("OFFLINE")
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

