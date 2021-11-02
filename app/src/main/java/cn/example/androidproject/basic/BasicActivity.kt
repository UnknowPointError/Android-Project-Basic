package cn.example.androidproject.basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import cn.example.androidproject.R
import cn.example.androidproject.Util
import cn.example.androidproject.databinding.BasicActiivtyBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.thread

@DelicateCoroutinesApi
class BasicActivity : AppCompatActivity() {
    private val updateComponent = 1
    private val mBinding by lazy { BasicActiivtyBinding.inflate(layoutInflater) }
    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                updateComponent -> {
                    mBinding.apply {
                        progreBar.visibility = View.INVISIBLE
                        val mainLinearTag = mainLinear.tag
                        if (mainLinearTag == null || mainLinearTag == "set1") {
                            mainLinear.setBackgroundResource(R.drawable.a1)
                            mainLinear.tag = "set2"
                            val value = resources.getColor(R.color.white, resources.newTheme())
                            edit1.setHintTextColor(value)
                            edit1.setTextColor(value)
                            edit2.setHintTextColor(value)
                            edit2.setTextColor(value)
                            text.setTextColor(value)
                        } else if (mainLinearTag == "set2") {
                            mainLinear.setBackgroundResource(R.drawable.a2)
                            mainLinear.tag = "set1"
                            val value = resources.getColor(R.color.black, resources.newTheme())
                            edit1.setHintTextColor(value)
                            edit1.setTextColor(value)
                            edit2.setHintTextColor(value)
                            edit2.setTextColor(value)
                            text.setTextColor(value)
                        }
                        imageBtn.tag = "Unset"
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initListener()
    }

    @DelicateCoroutinesApi
    private fun initListener() {
        mBinding.apply {
            imageBtn.setOnClickListener {
                if (imageBtn.tag != "Set") {
                    progreBar.visibility = View.VISIBLE
                    imageBtn.tag = "Set"
                    GlobalScope.launch {
                        delay(1000)
                        thread {
                            val msg = Message()
                            msg.what = updateComponent
                            handler.sendMessage(msg)
                        }
                    }
                }
            }
            splicBtn.setOnClickListener {
                when {
                    edit1.text.toString() == "" -> {
                        Util.showToast(this@BasicActivity, "Please input Username")
                    }
                    edit2.text.toString() == "" -> {
                        Util.showToast(this@BasicActivity, "Please input Password")
                    }
                    else -> {
                        text.autoLinkMask
                        val textData = "Username : ${edit1.text} \nPassword : ${edit2.text}"
                        text.text = textData
                    }
                }
            }
        }
    }
}
