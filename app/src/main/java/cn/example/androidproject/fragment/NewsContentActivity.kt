package cn.example.androidproject.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import cn.example.androidproject.R
import cn.example.androidproject.Util.typename
import cn.example.androidproject.databinding.NewsContentActivityBinding
import cn.example.androidproject.databinding.NewsContentFragBinding

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidproject/fragment
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 19:30
 * @Day:  星期四
 * @Description:
 **************************/
class NewsContentActivity : AppCompatActivity() {


    companion object {
        fun actionStart(context: Context, title: String, content: String) {
            val intent = Intent(context, NewsContentActivity::class.java).apply {
                putExtra("news_title", title)
                putExtra("news_content", content)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mBinding = NewsContentActivityBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        val title = intent.getStringExtra("news_title")
        val content = intent.getStringExtra("news_content")
        if (title != null && content != null) {
            NewsContentFragment.refresh(title, content)
        }
    }
}