package cn.example.androidProject.http

import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cn.example.androidProject.Util.showToasts
import cn.example.androidProject.databinding.HttpActivityBinding
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class HttpActivity : AppCompatActivity() {

    companion object {
        private const val HTTP_XML_DATA = "http://120.79.132.118/get_data.xml"
    }

    private val mBinding by lazy { HttpActivityBinding.inflate(layoutInflater) }
    private val main = this
    private var urlText: String = ""
    private var xmlText: StringBuilder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initComponent()
    }

    private fun initComponent() {
        mBinding.apply {
            webView.setOnClickListener { startActivity(Intent(main, WebViewActivity::class.java)) }
            myWebView.setOnClickListener { webViews() }
            httpUrConnection.setOnClickListener { sendRequestWithHttpURLConnection() }
            myHttpUrConnection.setOnClickListener { sendMyRequestWithHttpURLConnection() }
            myOkHttp.setOnClickListener { sendRequestWithMyOkHttp() }
            pullXmlData.setOnClickListener { pullXml() }
        }
    }

    private fun pullXml() {
        xmlText = null
        thread {
            try {
                val okHttpClient = OkHttpClient()
                val request = Request.Builder()
                    .url(HTTP_XML_DATA)
                    .build()
                val response = okHttpClient.newCall(request).execute()
                val responseData = response.body?.string()
                responseData?.let {
                    parseXMLWithPull(responseData)
                    showResponse(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun parseXMLWithPull(xmlData: String) {
        try {
            val i = 0
            val factory = XmlPullParserFactory.newInstance()
            val xmlPullParser = factory.newPullParser()
            xmlPullParser.setInput(StringReader(xmlData))
            var eventType = xmlPullParser.eventType
            var id = ""
            var name = ""
            var version = ""
            xmlText = StringBuilder()
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val nodeName = xmlPullParser.name
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        when (nodeName) {
                            "id" -> id = xmlPullParser.nextText()
                            "name" -> name = xmlPullParser.nextText()
                            "version" -> version = xmlPullParser.nextText()
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if ("app" == nodeName) {
                            xmlText?.append("id is $id\n")
                            xmlText?.append("name is $name\n")
                            xmlText?.append("version is $version\n")
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
            runOnUiThread {
                main.showToasts("$xmlText", true)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun sendRequestWithMyOkHttp() {
        thread {
            try {
                val okHttpClient = OkHttpClient()
                val request = Request.Builder()
                    .url("https://www.aliyun.com/")
                    .build()
                val response = okHttpClient.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    urlText = "获取阿里云网站数据成功"
                    showResponse(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun webViews() {
        val intent = Intent(main, WebViewActivity::class.java)
        intent.putExtra("MyWebView", "MyWeb")
        startActivity(intent)
    }

    private fun sendMyRequestWithHttpURLConnection() {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("https://www.minebbs.com")
                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.readTimeout = 8000
                connection.connectTimeout = 8000
                BufferedReader(InputStreamReader(connection.inputStream)).use {
                    it.forEachLine { str -> response.append(str) }
                    urlText = "获取mineBBS数据成功"
                    showResponse(response.toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun sendRequestWithHttpURLConnection() {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("https://www.baidu.com")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                connection.requestMethod = "GET"
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    it.forEachLine { str: String ->
                        response.append(str)
                    }
                    urlText = "获取百度数据成功"
                    showResponse(response.toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            mBinding.responseText.text = response
            if (urlText.isNotEmpty())
                main.showToasts("$urlText")
        }
    }
}
