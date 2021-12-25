package cn.example.androidProject.jetpack.myLiveData

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import cn.example.androidProject.R
import cn.example.androidProject.databinding.JetpackMylivedataActivityBinding

class MyLiveDataActivity : AppCompatActivity() {

    private val mBinding by lazy { JetpackMylivedataActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initComponent()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    private fun initComponent() = mBinding.apply {
        setSupportActionBar(myToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsingToolbar.setOnTouchListener { v, event ->
            myImageView.fitsSystemWindows = false
            true
        }
    }


}