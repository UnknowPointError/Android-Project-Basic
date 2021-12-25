package cn.example.androidProject.study.viewPager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import cn.example.androidProject.databinding.StudyViewpagerActivityBinding

class ViewPagerActivity : AppCompatActivity() {

    private val tabs = arrayListOf("tab1", "tab2", "tab3")
    private val views = arrayListOf<View>()
    private val mBinding by lazy { StudyViewpagerActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initUI()
    }

    private fun initUI() = mBinding.apply {
        val list = ArrayList<String>()
        list.add("页面一")
        list.add("页面二")
        list.add("页面三")
        list.add("页面四")
        viewPager.adapter = ViewPagerAdapter(this@ViewPagerActivity, list, viewPager)
    }
}
