package cn.example.androidProject.jetpack.liveData

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
import cn.example.androidProject.databinding.JetpackLivedataActivityBinding
import cn.example.androidProject.jetpack.MyObserver
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class LiveDataActivity : AppCompatActivity() {

    private val mBinding by lazy { JetpackLivedataActivityBinding.inflate(layoutInflater) }
    private lateinit var viewModel: LiveDataMainViewModel
    private lateinit var sp: SharedPreferences
    private var auto = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        MyApplication.context = this
        initComponent()
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
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

    private fun initComponent() {
        setSupportActionBar(mBinding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        lifecycle.addObserver(MyObserver())
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        viewModel = ViewModelProvider(this,
            LiveDataMainViewModelFactory(countReserved)).get(LiveDataMainViewModel::class.java)
        mBinding.apply {
            plusOne.setOnClickListener { viewModel.plusOne() }
            clear.setOnClickListener { viewModel.clear() }
            startAutoPlus.setOnClickListener {
                if (!auto) thread {
                    runOnUiThread {
                        startAutoPlus.visibility = View.GONE
                        stopAutoPlus.visibility = View.VISIBLE
                    }
                    if (!auto) auto = true
                    while (auto) {
                        runBlocking {
                            var text = inputTime.text.toString()
                            var time = if (text.isNotEmpty() && text.first() != '0') {
                                text.toLong()
                            } else 1000
                            val job1 = launch {
                                delay(time)
                                runOnUiThread {
                                    viewModel.plusOne()
                                }
                            }
                            inputTime.addTextChangedListener {
                                text = inputTime.text.toString()
                                time = if (text.isNotEmpty() && text.first() != '0') {
                                    job1.cancel()
                                    text.toLong()
                                } else 1000
                            }
                        }
                    }
                }
            }
            getUser.setOnClickListener {
                val userId = (0..10000).random().toString()
                viewModel.getUser(userId = userId)
            }
            stopAutoPlus.visibility = View.GONE
            stopAutoPlus.setOnClickListener {
                if (auto) {
                    auto = false
                    runOnUiThread {
                        startAutoPlus.visibility = View.VISIBLE
                        stopAutoPlus.visibility = View.GONE
                    }
                }
            }
            viewModel.counter.observe(this@LiveDataActivity, { count ->
                infoText.text = count.toString()
            })
            viewModel.user.observe(this@LiveDataActivity, { user ->
                infoText.text = user.firstName
            })
        }
    }

    private fun refreshCounter() {
        mBinding.infoText.text = viewModel.counter.toString()
    }
}
