package cn.example.androidProject.study.tabLayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.example.androidProject.databinding.StudyTablayoutActivityBinding
import com.google.android.material.tabs.TabLayoutMediator

class TabLayoutActivity : AppCompatActivity() {

    private val mBinding by lazy { StudyTablayoutActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initUI()
    }

    private fun initUI() = mBinding.apply {
        val adapter = TabLayoutViewPagerAdapter(this@TabLayoutActivity)
        viewPager2.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "First"
                1 -> tab.text = "Second"
                else -> tab.text = "Third"
            }
        }.attach()
    }
}