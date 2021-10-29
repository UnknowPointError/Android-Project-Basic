package cn.example.androidproject.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cn.example.androidproject.R
import cn.example.androidproject.databinding.NewsActivityBinding

class NewsActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        lateinit var mmBinding: NewsActivityBinding
    }
    val mBinding by lazy { NewsActivityBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mmBinding = mBinding
        mBinding
    }
}