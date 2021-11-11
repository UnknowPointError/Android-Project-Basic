package cn.example.androidProject.http

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import cn.example.androidProject.MyApplication
import cn.example.androidProject.databinding.WebViewActivityBinding

class WebViewActivity : AppCompatActivity() {

    private val mBinding by lazy { WebViewActivityBinding.inflate(layoutInflater) }
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
MyApplication.context = this
        supportActionBar?.hide()
        val value = intent.getStringExtra("MyWebView")
        mBinding.apply {
            url = "https://www.baidu.com"
            value?.let { url = "https://www.minebbs.com" }
            webView.settings.javaScriptEnabled = true
            webView.webViewClient = WebViewClient()
            webView.loadUrl(url)
        }
    }
}