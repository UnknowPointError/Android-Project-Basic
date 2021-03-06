package cn.example.androidProject.materialDesign.toolBar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import cn.example.androidProject.MyApplication
import cn.example.androidProject.R
import cn.example.androidProject.databinding.MaterialToolbarActivityBinding
import cn.example.androidProject.util.Util.showToast

class ToolBarActivity : AppCompatActivity() {

    private val mBinding by lazy { MaterialToolbarActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        MyApplication.context = this
        setSupportActionBar(mBinding.toolBar)
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
        }
        return true
    }
}