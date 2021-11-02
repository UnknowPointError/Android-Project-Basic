package cn.example.androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import cn.example.androidproject.Util.typename
import cn.example.androidproject.basic.BasicActivity
import cn.example.androidproject.databinding.ActivityMainBinding
import cn.example.androidproject.fragment.NewsActivity
import cn.example.androidproject.listview.ListActivity
import cn.example.androidproject.recyclertalk.TalkActivity
import cn.example.androidproject.recyclerview.RecyclerActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        Log.d("eee", "${LayoutInflater.from(this@MainActivity).typename()}")
        initComponent()
    }

    private fun initComponent() {
        mBinding.apply {
            basicBtn.setOnClickListener(this@MainActivity)
            basicListviewBtn.setOnClickListener(this@MainActivity)
            listviewBtn.setOnClickListener(this@MainActivity)
            recyclerViewGrid.setOnClickListener(this@MainActivity)
            recyclerViewLinear.setOnClickListener(this@MainActivity)
            talk.setOnClickListener(this@MainActivity)
            news.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(v: View?) {
        mBinding.apply {
            when (v?.id) {
                basicBtn.id -> startActivity<BasicActivity> { }
                basicListviewBtn.id -> startActivity<ListActivity> {
                    putExtra("listData", "Basic")
                }
                listviewBtn.id -> startActivity<ListActivity> { }
                recyclerViewGrid.id -> startActivity<RecyclerActivity> {
                    putExtra("RecyclerView", "Grid")
                }
                recyclerViewLinear.id -> startActivity<RecyclerActivity> { }
                talk.id -> startActivity<TalkActivity> { }
                news.id -> startActivity<NewsActivity> { }
            }
        }
    }

    private inline fun <reified T> startActivity(block: Intent.() -> Unit) {
        val intent = Intent(this, T::class.java)
        intent.block()
        this.startActivity(intent)
    }
}