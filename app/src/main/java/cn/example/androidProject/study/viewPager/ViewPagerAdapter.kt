package cn.example.androidProject.study.viewPager

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import android.R
import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import cn.example.androidProject.databinding.StudyViewpagerItemBinding


/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidProject/study/login
 * @Time: 2021 16:43 / 12月
 * @Author: BarryAllen
 * TODO: ViewPagerAdapter
 **************************/


/**
 * @Author: wuchaowen
 * @Description:
 * @Time:
 */
class ViewPagerAdapter(
    context: Context?,
    val data: List<String>,
    private val viewPager2: ViewPager2
) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private val colorArray = intArrayOf(
        R.color.black,
        R.color.holo_blue_dark,
        R.color.holo_green_dark,
        R.color.holo_red_dark
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mBinding = StudyViewpagerItemBinding.bind(
            mInflater
                .inflate(cn.example.androidProject.R.layout.study_viewpager_item, parent, false)
        )
        return ViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animal = data[position]
        holder.myTextView.text = animal
        holder.relativeLayout.setBackgroundResource(colorArray[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder internal constructor(mBinding: StudyViewpagerItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        var myTextView: TextView = mBinding.tvTitle
        var relativeLayout: RelativeLayout = mBinding.container
    }

}

