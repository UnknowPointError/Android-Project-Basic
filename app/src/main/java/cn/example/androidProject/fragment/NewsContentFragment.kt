package cn.example.androidProject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.example.androidProject.databinding.NewsContentFragBinding

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidProject/fragment
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 19:01
 * @Day:  星期四
 * @Description:
 **************************/
class NewsContentFragment : Fragment() {


    private lateinit var mBinding: NewsContentFragBinding

    fun refresh(title: String, content: String) {
        mBinding.apply {
            contentLayout.visibility = View.VISIBLE
            newsTitle.text = title
            newsContent.text = content
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mBinding = NewsContentFragBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        System.gc()
    }
}