package cn.example.androidProject.recyclerView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cn.example.androidProject.R
import cn.example.androidProject.util.Util
import cn.example.androidProject.databinding.RecyclerActivityBinding
import cn.example.androidProject.listView.Fruit

/*************************
 * @ClassName: RecyclerActivity.kt
 * @Dir_Path: cn\example\androidProject\recyclerView\RecyclerActivity.kt
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/3 22:55 周三
 * @Description:
 **************************/
class RecyclerActivity : AppCompatActivity() {

    private val mBinding by lazy { RecyclerActivityBinding.inflate(layoutInflater) }
    private val fruitData = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initComponent()
    }

    private fun initComponent() {
        initFruit()
        val adapter = RecyclerAdapter(fruitData) {
            val str = "You click the name is ${it.fruitName}\nID is ${it.id}"
            Util.showToast(this, str)
        }
        val layoutManager = when (intent.getStringExtra("RecyclerView")) {
            "Grid" -> GridLayoutManager(this, 2)
            else -> LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
        mBinding.recycler.layoutManager = layoutManager
        mBinding.recycler.adapter = adapter
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

