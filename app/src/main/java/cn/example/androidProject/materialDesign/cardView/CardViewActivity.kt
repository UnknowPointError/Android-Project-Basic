package cn.example.androidProject.materialDesign.cardView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import cn.example.androidProject.MyApplication
import cn.example.androidProject.R
import cn.example.androidProject.databinding.MaterialCardviewActivityBinding
import cn.example.androidProject.listView.Fruit
import cn.example.androidProject.util.Util.showToast
import java.util.ArrayList
import kotlin.concurrent.thread

// TODO 当前模块还包含AppBarLayout
// TODO APPBarLayout: 实际是一个垂直方向的LinearLayout，内部有很多滚动事件的封装

class CardViewActivity : AppCompatActivity() {

    private val mBinding by lazy { MaterialCardviewActivityBinding.inflate(layoutInflater) }
    private val fruitList = ArrayList<Fruit>()
    private val main = this
    private val fruits =
        mutableListOf(Fruit(R.drawable.apple, "Apple"), Fruit(R.drawable.banana, "Banana"),
            Fruit(R.drawable.orange, "Orange"),
            Fruit(R.drawable.watermelon, "Watermelon"), Fruit(R.drawable.pear, "Pear"),
            Fruit(R.drawable.grape, "Grape"), Fruit(R.drawable.pineapple, "Pineapple"),
            Fruit(R.drawable.strawberry, "Strawberry"), Fruit(R.drawable.cherry, "Cherry"),
            Fruit(R.drawable.mango, "Mango"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        MyApplication.context = this
        setSupportActionBar(mBinding.toolBar)
        initFruits()
        initComponent()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> showToast("You Clicked Backup.")
            R.id.delete -> showToast("You Clicked Delete.")
            R.id.settings -> showToast("You Clicked Settings.")
            android.R.id.home -> mBinding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    private fun initComponent() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        mBinding.apply {
            val layoutManager = GridLayoutManager(main, 2)
            val adapter = MaterialRecyclerViewAdapter(main, fruitList)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            swipeRefresh.setColorSchemeResources(R.color.design_default_color_primary)
            swipeRefresh.setOnRefreshListener {
                refreshFruits(adapter)
            }
        }
    }

    private fun refreshFruits(adapter: MaterialRecyclerViewAdapter) {
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                initFruits()
                adapter.notifyDataSetChanged()
                mBinding.swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun initFruits() {
        repeat(100) {
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }
}