package cn.example.androidProject.study.tabLayout

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.example.androidProject.fragment.NewsTitleFragment

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidProject/study/tabLayout
 * @Time: 2021 17:33 / 12月
 * @Author: BarryAllen
 * TODO: ViewPagerAdapter
 **************************/
class TabLayoutViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewsTitleFragment()
            1 -> NewsTitleFragment()
            else -> NewsTitleFragment()
        }
    }
}