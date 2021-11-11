package cn.example.androidProject.jetpack.viewModel

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import cn.example.androidProject.MyApplication
import cn.example.androidProject.R
import cn.example.androidProject.databinding.JetpackViewmodelActivityBinding
import cn.example.androidProject.jetpack.MyObserver
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class ViewModelActivity : AppCompatActivity() {

    private val mBinding by lazy { JetpackViewmodelActivityBinding.inflate(layoutInflater) }
    private lateinit var viewModel: MainViewModel
    private lateinit var sp: SharedPreferences
    private var auto = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        MyApplication.context = this
        initComponent()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter)
        }
    }

    private fun initComponent() {
        setSupportActionBar(mBinding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        lifecycle.addObserver(MyObserver())
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        viewModel = ViewModelProvider(this,
            MainViewModelFactory(countReserved)).get(MainViewModel::class.java)
        mBinding.apply {
            plusOne.setOnClickListener {
                viewModel.counter++
                refreshCounter()
            }
            clear.setOnClickListener {
                viewModel.counter = 0
                refreshCounter()
            }
            startAutoPlus.setOnClickListener {
                if (!auto) thread {
                    if (!auto) {
                        auto = true
                        runOnUiThread {
                            startAutoPlus.visibility = View.GONE
                            stopAutoPlus.visibility = View.VISIBLE
                        }
                    }
                    while (auto) {
                        runBlocking {
                            var text = inputTime.text.toString()
                            var time = if (text.isNotEmpty() && text.first() != '0') {
                                text.toLong()
                            } else 1000
                            val job1 = launch {
                                delay(time)
                                viewModel.counter++
                            }
                            inputTime.addTextChangedListener {
                                text = inputTime.text.toString()
                                time = if (text.isNotEmpty() && text.first() != '0') {
                                    job1.cancel()
                                    text.toLong()
                                } else 1000
                            }
                        }
                        runOnUiThread { refreshCounter() }
                    }
                }
            }
            stopAutoPlus.visibility = View.GONE
            stopAutoPlus.setOnClickListener {
                if (auto) {
                    runOnUiThread {
                        startAutoPlus.visibility = View.GONE
                        stopAutoPlus.visibility = View.VISIBLE
                    }
                    auto = false
                }
            }
            refreshCounter()
        }
    }

    private fun refreshCounter() {
        mBinding.infoText.text = viewModel.counter.toString()
    }
}