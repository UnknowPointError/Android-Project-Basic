package cn.example.androidProject.materialDesign.drawerLayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import cn.example.androidProject.R
import cn.example.androidProject.databinding.MaterialDrawerlayoutActivityBinding
import cn.example.androidProject.util.Util.showToasts

class DrawerLayoutActivity : AppCompatActivity() {

    private val mBinding by lazy { MaterialDrawerlayoutActivityBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        setSupportActionBar(mBinding.toolBar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> showToasts("You Clicked Backup.")
            R.id.delete -> showToasts("You Clicked Delete.")
            R.id.settings -> showToasts("You Clicked Settings.")
            android.R.id.home -> mBinding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }
}