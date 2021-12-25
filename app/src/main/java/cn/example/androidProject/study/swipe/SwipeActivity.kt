package cn.example.androidProject.study.swipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.example.androidProject.databinding.StudySwipeActivityBinding

class SwipeActivity : AppCompatActivity() {

    private val mBinding by lazy {StudySwipeActivityBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initView()
    }

    private fun initView() {

    }
}