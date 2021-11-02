package cn.example.androidproject.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import cn.example.androidproject.R
import cn.example.androidproject.databinding.NewsActivityBinding

class NewsActivity : AppCompatActivity() {

    var mmBinding: NewsActivityBinding? = null
    val mBinding by lazy { NewsActivityBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("eeee", "asdew")
        setContentView(mBinding.root)
//        Log.e("eeee", "asd${mBinding..findViewById}")
        mmBinding = mBinding
    }
}