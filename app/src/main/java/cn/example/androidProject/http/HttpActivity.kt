package cn.example.androidProject.http

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import cn.example.androidProject.MyApplication
import cn.example.androidProject.util.Util.showToast
import cn.example.androidProject.databinding.HttpActivityBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONArray
import org.xml.sax.Attributes
import org.xml.sax.InputSource
import org.xml.sax.helpers.DefaultHandler
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader
import java.lang.StringBuilder
import java.lang.reflect.Type
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.SAXParserFactory
import kotlin.concurrent.thread

class HttpActivity : AppCompatActivity() {

    inner class ContentHandler : DefaultHandler() {

        private var nodeName = ""
        private var xmlText1 = ""
        private var xmlText2 = ""
        private lateinit var id: StringBuilder
        private lateinit var name: StringBuilder
        private lateinit var version: StringBuilder

        override fun startDocument() {
            id = StringBuilder()
            name = StringBuilder()
            version = StringBuilder()
        }

        override fun startElement(
            uri: String,
            localName: String,
            qName: String,
            attributes: Attributes,
        ) {
            nodeName = localName
            xmlText1 += "uri is $uri\n"
            xmlText1 += "localName is $localName\n"
            xmlText1 += "qName is $qName\n"
            xmlText1 += "attributes is $attributes\n"
            xmlText1 += "-----------------------\n"
        }

        override fun characters(ch: CharArray?, start: Int, length: Int) {
            when (nodeName) {
                "id" -> id.append(ch, start, length)
                "name" -> name.append(ch, start, length)
                "version" -> version.append(ch, start, length)
            }
        }

        override fun endElement(uri: String?, localName: String, qName: String?) {
            if ("app" == localName) {
                xmlText2 += "id is ${id.toString().trim()}\n"
                xmlText2 += "name is ${name.toString().trim()}\n"
                xmlText2 += "version is ${version.toString().trim()}\n"
                xmlText2 += "===================\n"
                id.clear()
                name.clear()
                version.clear()
            }
        }

        override fun endDocument() {
            runOnUiThread {
                val text = xmlText2 + xmlText1
                mBinding.responseText.text = text
                mBinding.progressBar.visibility = View.INVISIBLE
            }
        }
    }

    class App(val id: String, val name: String, val version: String)

    companion object {
        private const val HTTP_XML_DATA = "http://120.79.132.118/get_data.xml"
        private const val HTTP_JSON_DATA = "http://120.79.132.118/get_data.json"
        private const val RETROFIT_JSON_DATA = "http://120.79.132.118/"
    }

    private val mBinding by lazy { HttpActivityBinding.inflate(layoutInflater) }
    private val main = this
    private var isRun = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        MyApplication.context = this
        initComponent()
    }

    private fun initComponent() {
        mBinding.apply {
            webView.setOnClickListener {
                if (!isRun) startActivity(Intent(main, WebViewActivity::class.java))
            }
            myWebView.setOnClickListener { if (!isRun) webViews() }
            httpUrConnection.setOnClickListener { if (!isRun) sendRequestWithHttpURLConnection() }
            myHttpUrConnection.setOnClickListener { if (!isRun) sendMyRequestWithHttpURLConnection() }
            myOkHttp.setOnClickListener { if (!isRun) sendRequestWithMyOkHttp() }
            pullXmlData.setOnClickListener { if (!isRun) sendRequestWithPullXMLConnection() }
            saxXmlData.setOnClickListener { if (!isRun) sendRequestWithSaxXMLConnection() }
            parseJsonWithJsonObject.setOnClickListener { if (!isRun) sendRequestWithJsonObjectConnection() }
            parseJsonWithGson.setOnClickListener { if (!isRun) sendRequestWithGsonConnection() }
            retrofit.setOnClickListener { if (!isRun) retroFit() }
        }
    }

    private fun webViews() {
        val intent = Intent(main, WebViewActivity::class.java)
        intent.putExtra("MyWebView", "MyWeb")
        startActivity(intent)
    }

    private fun retroFit() {
        isRun = true
        mBinding.progressBar.visibility = View.VISIBLE
        val retrofit = Retrofit.Builder()
            .baseUrl(RETROFIT_JSON_DATA)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val appService = retrofit.create(AppService::class.java)
        appService.getAppData().enqueue(object : Callback<List<App>> {
            /**
             * Invoked for a received HTTP response.
             *
             *
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call [Response.isSuccessful] to determine if the response indicates success.
             */
            override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {
                var responseText = ""
                val list = response.body()
                list?.let {
                    for (app in list) {
                        responseText += "${app.id}   ${app.name}   ${app.version}\n"
                    }
                    runOnUiThread {
                        showToast(responseText)
                        mBinding.responseText.text = responseText
                        mBinding.progressBar.visibility = View.INVISIBLE
                        isRun = false
                    }
                }
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected exception
             * occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<List<App>>, t: Throwable) {
                runOnUiThread {
                    showToast("获取JSON数据失败，请检查网络是否开启！！")
                    mBinding.progressBar.visibility = View.INVISIBLE
                    isRun = false
                }
                t.printStackTrace()
            }

        })
    }

    private fun sendRequestWithGsonConnection() {
        isRun = true
        mBinding.progressBar.visibility = View.VISIBLE
        thread {
            val connection = OkHttpClient()
            try {
                val request = Request.Builder()
                    .url(HTTP_JSON_DATA)
                    .build()
                val response = connection.newCall(request).execute()
                val responseData = response.body?.string()
                runOnUiThread {
                    responseData?.let {
                        parseJsonWithGson(responseData)
                        mBinding.responseText.text = responseData
                        mBinding.progressBar.visibility = View.INVISIBLE
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    showToast("获取JSON数据失败，请检查网络是否开启！！")
                    mBinding.progressBar.visibility = View.INVISIBLE
                }
                e.printStackTrace()
            } finally {
                if (isRun) isRun = false
            }
        }
    }

    private fun sendRequestWithJsonObjectConnection() {
        isRun = true
        mBinding.progressBar.visibility = View.VISIBLE
        thread {
            val connection = OkHttpClient()
            try {
                val request = Request.Builder()
                    .url(HTTP_JSON_DATA)
                    .build()
                val response = connection.newCall(request).execute()
                val responseData = response.body?.string()
                runOnUiThread {
                    responseData?.let {
                        parseJsonWithJsonObject(responseData)
                        mBinding.responseText.text = responseData
                        mBinding.progressBar.visibility = View.INVISIBLE
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    showToast("获取JSON数据失败，请检查网络是否开启！")
                    mBinding.progressBar.visibility = View.INVISIBLE
                }
                e.printStackTrace()
            } finally {
                if (isRun) isRun = false
            }
        }
    }

    private fun sendRequestWithMyOkHttp() {
        isRun = true
        mBinding.progressBar.visibility = View.VISIBLE
        thread {
            try {
                val okHttpClient = OkHttpClient()
                val request = Request.Builder()
                    .url("https://www.aliyun.com/")
                    .build()
                val response = okHttpClient.newCall(request).execute()
                val responseData = response.body?.string()
                runOnUiThread {
                    if (responseData != null) {
                        mBinding.responseText.text = responseData
                        showToast("获取阿里云网站数据成功")
                        mBinding.progressBar.visibility = View.INVISIBLE
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    showToast("获取阿里云网站数据失败，请检查网络是否开启！！")
                    mBinding.progressBar.visibility = View.INVISIBLE
                }
                e.printStackTrace()
            } finally {
                if (isRun) isRun = false
            }
        }
    }

    private fun sendMyRequestWithHttpURLConnection() {
        isRun = true
        mBinding.progressBar.visibility = View.VISIBLE
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
                    runOnUiThread {
                        mBinding.responseText.text = response
                        showToast("获取MineBBS数据成功")
                        mBinding.progressBar.visibility = View.INVISIBLE
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    showToast("获取MineBBs网站数据失败，请检查网络是否开启！！")
                    mBinding.progressBar.visibility = View.INVISIBLE
                }
                e.printStackTrace()
            } finally {
                connection?.disconnect()
                if (isRun) isRun = false
            }
        }
    }

    private fun sendRequestWithHttpURLConnection() {
        isRun = true
        mBinding.progressBar.visibility = View.VISIBLE
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
                }
                runOnUiThread {
                    mBinding.responseText.text = response.toString()
                    showToast("获取百度数据成功")
                    mBinding.progressBar.visibility = View.INVISIBLE
                }
            } catch (e: Exception) {
                runOnUiThread {
                    showToast("获取百度网站数据失败，请检查网络是否开启！！")
                    mBinding.progressBar.visibility = View.INVISIBLE
                }
                e.printStackTrace()
            } finally {
                connection?.disconnect()
                if (isRun) isRun = false
            }
        }
    }

    private fun sendRequestWithPullXMLConnection() {
        isRun = true
        mBinding.progressBar.visibility = View.VISIBLE
        thread {
            try {
                val okHttpClient = OkHttpClient()
                val request = Request.Builder()
                    .url(HTTP_XML_DATA)
                    .build()
                val response = okHttpClient.newCall(request).execute()
                val responseData = response.body?.string()
                runOnUiThread {
                    responseData?.let {
                        parseXMLWithPull(responseData)
                        mBinding.responseText.text = responseData
                        mBinding.progressBar.visibility = View.INVISIBLE
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    showToast("获取XML数据失败，请检查网络是否开启！！")
                    mBinding.progressBar.visibility = View.INVISIBLE
                }
                e.printStackTrace()
            } finally {
                if (isRun) isRun = false
            }
        }
    }

    private fun sendRequestWithSaxXMLConnection() {
        isRun = true
        mBinding.progressBar.visibility = View.VISIBLE
        thread {
            val connection = OkHttpClient()
            try {
                val request = Request.Builder()
                    .url(HTTP_XML_DATA)
                    .build()
                val response = connection.newCall(request).execute()
                val responseData = response.body?.string()
                responseData?.let {
                    parseXMLWithSAX(responseData)
                }
            } catch (e: Exception) {
                runOnUiThread {
                    showToast("获取XML数据失败，请检查网络是否开启！！")
                    mBinding.progressBar.visibility = View.INVISIBLE
                }
                e.printStackTrace()
            } finally {
                if (isRun) isRun = false
            }
        }
    }

    private fun parseJsonWithJsonObject(jsonData: String) {
        var jsonText = ""
        try {
            val jsonArray = JSONArray(jsonData)
            val jsonArrayLength = jsonArray.length()
            for (i in 0 until jsonArrayLength) {
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getString("id")
                val name = jsonObject.getString("name")
                val version = jsonObject.getString("version")
                jsonText += "$id   $name   $version\n"
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        runOnUiThread {
            showToast(jsonText)
        }
    }

    private fun parseJsonWithGson(jsonData: String) {
        var gsonText = ""
        val gson = Gson()
        val typeOf: Type = object : TypeToken<List<App>>() {}.type
        val appList: List<App> = gson.fromJson(jsonData, typeOf)
        appList.forEach {
            gsonText += "${it.id}   ${it.name}   ${it.version}\n"
        }
        runOnUiThread {
            showToast(gsonText, true)
        }
    }

    private fun parseXMLWithSAX(xmlData: String) {
        try {
            val factory = SAXParserFactory.newInstance()
            val xmlReader = factory.newSAXParser().xmlReader
            val handler = ContentHandler()
            xmlReader.contentHandler = handler
            xmlReader.parse(InputSource(StringReader(xmlData)))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun parseXMLWithPull(xmlData: String) {
        try {
            val xmlText = StringBuilder()
            val factory = XmlPullParserFactory.newInstance()
            val xmlPullParser = factory.newPullParser()
            xmlPullParser.setInput(StringReader(xmlData))
            var eventType = xmlPullParser.eventType
            var id = ""
            var name = ""
            var version = ""
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
                            xmlText.append("id is $id\n")
                            xmlText.append("name is $name\n")
                            xmlText.append("version is $version\n")
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
            runOnUiThread {
                showToast("$xmlText", true)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}