package cn.example.androidproject.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.example.androidproject.R
import cn.example.androidproject.Util
import cn.example.androidproject.databinding.RecyclerActivityBinding
import cn.example.androidproject.listview.Fruit

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
            Util.showtoast(this, str)
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
