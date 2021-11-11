package cn.example.androidProject.storage.filePersistence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import cn.example.androidProject.MyApplication
import cn.example.androidProject.databinding.StorageActivityBinding
import java.io.*
import java.lang.StringBuilder

/*************************
 * @ClassName: FileActivity.kt
 * @Dir_Path: cn\example\androidProject\storage\filePersistence\FileActivity.kt
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/3 22:35 周三
 * @Description:
 **************************/
class FileActivity : AppCompatActivity(), View.OnClickListener {

    private val mBinding by lazy { StorageActivityBinding.inflate(layoutInflater) }
    private val main = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        MyApplication.context = this
        initComponent()
    }

    override fun onClick(v: View?) {
        mBinding.apply {
            when (v?.id) {
                saveData.id -> {
                    try {
                        val output = openFileOutput("data", MODE_PRIVATE)
                        val writer = BufferedWriter(OutputStreamWriter(output))
                        writer.use {
                            it.write(inputText.text.toString())
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                readData.id -> {
                    val content = StringBuilder()
                    try {
                        val input = openFileInput("data")
                        val reader = BufferedReader(InputStreamReader(input))
                        reader.use { it.forEachLine { data -> content.append(data) } }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    inputText.setText(content.toString())
                    inputText.setSelection(content.length)
                }
            }
        }
    }

    private fun initComponent() {
        mBinding.apply {
            saveData.setOnClickListener(main)
            readData.setOnClickListener(main)
            val saveText = "File Storage Save"
            val readText = "File Storage Read"
            saveData.text = saveText
            readData.text = readText
        }
    }

}