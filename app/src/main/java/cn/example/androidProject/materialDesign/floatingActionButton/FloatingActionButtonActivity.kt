package cn.example.androidProject.materialDesign.floatingActionButton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import cn.example.androidProject.MyApplication
import cn.example.androidProject.R
import cn.example.androidProject.databinding.MaterialFloatingbuttonActivityBinding
import cn.example.androidProject.util.Util.showSnackbar
import cn.example.androidProject.util.Util.showToast

// TODO 此模块还包含SnackBar控件、CoordinatorLayout以及NavigationView控件
// TODO CoordinatorLayout:作用和FrameLayout基本一致，但有额外的Material能力
// TODO 实际上CoordinatorLayout可以监听所有子控件的事件并自动做出合理的相应

class FloatingActionButtonActivity : AppCompatActivity() {

    private val mBinding by lazy { MaterialFloatingbuttonActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        MyApplication.context = this
        setSupportActionBar(mBinding.toolBar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        mBinding.floatingActionButton.setOnClickListener {
            it.showSnackbar("Data delete.", "Undo") {
                showToast("Data restored.")
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