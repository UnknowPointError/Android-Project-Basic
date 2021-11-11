package cn.example.androidProject.materialDesign.collapsingToolBarLayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import cn.example.androidProject.MyApplication
import cn.example.androidProject.R
import cn.example.androidProject.databinding.MaterialCollapsingActivityBinding
import com.bumptech.glide.Glide

class CollapsingToolBarActivity : AppCompatActivity() {

    companion object {
        const val FRUIT_NAME = "fruit_name"
        const val FRUIT_IMAGE_ID = "fruit_image_id"
    }

    private val mBinding by lazy { MaterialCollapsingActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        MyApplication.context = this
        initComponent()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    private fun initComponent() {
        val fruitName = intent.getStringExtra(FRUIT_NAME) ?: "This is CollapsingToolBarActivity"
        val fruitImageID = intent.getIntExtra(FRUIT_IMAGE_ID, R.drawable.a7)
        setSupportActionBar(mBinding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.collapsingToolbar.title = fruitName
        Glide.with(this).load(fruitImageID).into(mBinding.fruitImageView)
        mBinding.fruitContentText.text = generateFruitContent(fruitName)
    }

    private fun generateFruitContent(fruitName: String): CharSequence = fruitName.repeat(500)
}