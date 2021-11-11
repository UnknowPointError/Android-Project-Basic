package cn.example.androidProject.storage.sharedPreferences

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import cn.example.androidProject.MyApplication
import cn.example.androidProject.databinding.StorageActivityBinding

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidProject/storage/sharedPreferences
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/3 16:51
 * @Day:  星期三
 * @Description:
 **************************/
class SharedPreferencesActivity : AppCompatActivity(), View.OnClickListener {

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
                    getSharedPreferences("data", MODE_PRIVATE).edit().apply {
                        putString("name", "Barry")
                        putInt("age", 17)
                        putBoolean("work", false)
                        apply()
                    }
                }
                readData.id -> {
                    val getText = inputText.text.toString()
                    if (getText.isNotEmpty() && getText.isNotEmpty()) {
                        getSharedPreferences("data", MODE_PRIVATE).apply {
                            val name = getString("name", "")
                            val age = getInt("age", 0)
                            val work = getBoolean("work", true)
                            val text: MutableList<String> = mutableListOf("$name", "$age", "$work")
                            getText.forEach {
                                if (it.toString() == " ") text.add("null") else text.add("$it")
                            }
                            listView.adapter = ArrayAdapter(main,
                                android.R.layout.simple_list_item_1,
                                text)
                        }
                    }
                }
            }
        }
    }

    private fun initComponent() {
        mBinding.apply {
            saveData.setOnClickListener(main)
            readData.setOnClickListener(main)
            val strSave = "SharedPreference Save"
            val strRead = "SharedPreference Read"
            saveData.text = strSave
            readData.text = strRead
        }
    }

}