package cn.example.androidproject.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import cn.example.androidproject.R
import cn.example.androidproject.Util.showtoast
import cn.example.androidproject.databinding.ListActivityBinding
import kotlin.collections.ArrayList

class ListActivity : AppCompatActivity() {

    private val mBinding by lazy { ListActivityBinding.inflate(layoutInflater) }
    private val fruitData = ArrayList<Fruit>()
    private val tag = "ListView"
    private val data by lazy { initData() }
    private fun initData(): ArrayList<String> {
        val getList = ArrayList<String>()
        repeat(10) {
            getList.addAll(
                listOf(
                    "Barry", "Allen", "Wallet", "Jason", "Timer", "Setter", "Getter",
                    "ForEach", "Commit", "Cursor", "Factory", "Expand", "Bitmap", "View",
                    "Thread", "Coroutines", "Kotlin", "Java", "C++", "C#", "Android Studio"
                )
            )
        }
        return getList
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initComponent()
    }

    private fun initComponent() {
        val value = intent.getStringExtra("listData")
        showtoast(this, "value = $value")
        if (value == "Basic") {
            mBinding.listview.adapter =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        } else {
            /*val adapter = ListAdapter(this, R.layout.fruit, fruitData) {
                Log.e("ListActivity", "fruit -> $it")
            }
            此写法由大佬404编写*/
            initFruit()
            val adapter = ListAdapter(this, R.layout.fruit, fruitData)
            mBinding.listview.adapter = adapter
        }
        mBinding.listview.setOnItemClickListener { parent, view, position, id ->
            val strA = "\nParent is $parent\nView is $view\nPosition is $position\nID is $id"
            Log.d(tag,strA)
            if (fruitData.isNotEmpty()) {
                val str = "You click the ID is $id\n FruitName is ${fruitData[position].fruitName} "
                showtoast(this, text = str)
            } else {
                val str = "You click the ID is $id\n Name is ${data[position]} "
                showtoast(this, str)
            }
        }
    }

    private fun initFruit() {
        repeat(20) {
            fruitData.add(Fruit(R.drawable.apple_pic, "Apple"))
            fruitData.add(Fruit(R.drawable.banana_pic, "Banana"))
            fruitData.add(Fruit(R.drawable.cherry_pic, "Cherry"))
            fruitData.add(Fruit(R.drawable.grape_pic, "Grape"))
            fruitData.add(Fruit(R.drawable.mango_pic, "Mango"))
            fruitData.add(Fruit(R.drawable.orange_pic, "Orange"))
            fruitData.add(Fruit(R.drawable.pear_pic, "Pear"))
            fruitData.add(Fruit(R.drawable.pineapple_pic, "Pineapple"))
            fruitData.add(Fruit(R.drawable.strawberry_pic, "Strawberry"))
            fruitData.add(Fruit(R.drawable.watermelon_pic, "Watermelon"))
        }
    }
}
