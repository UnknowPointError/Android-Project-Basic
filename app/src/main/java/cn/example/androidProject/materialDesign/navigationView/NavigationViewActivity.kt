package cn.example.androidProject.materialDesign.navigationView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import cn.example.androidProject.MyApplication
import cn.example.androidProject.R
import cn.example.androidProject.databinding.MaterialNavigationviewActivityBinding
import cn.example.androidProject.util.Util.showToast

class NavigationViewActivity : AppCompatActivity() {

    private val mBinding by lazy { MaterialNavigationviewActivityBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        MyApplication.context = this
        setSupportActionBar(mBinding.toolBar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        mBinding.apply {
            navView.setCheckedItem(R.id.navCall)
            navView.setNavigationItemSelectedListener {
                drawerLayout.closeDrawers()
                true
            }
        }
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
}