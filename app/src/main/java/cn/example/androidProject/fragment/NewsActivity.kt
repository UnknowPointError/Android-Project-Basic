package cn.example.androidProject.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import cn.example.androidProject.R
import cn.example.androidProject.databinding.NewsActivityBinding

/*************************
 * @ClassName: NewsActivity.kt
 * @Dir_Path: cn\example\androidProject\fragment\NewsActivity.kt
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/3 22:29 周三
 * @Description:
 **************************/
class NewsActivity : AppCompatActivity() {

    val mBinding by lazy { NewsActivityBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

    }
}