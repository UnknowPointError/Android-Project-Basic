package cn.example.androidproject.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.example.androidproject.Util
import cn.example.androidproject.databinding.NewsContentFragBinding

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidproject/fragment
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
        savedInstanceState: Bundle?
    ): View? {
        mBinding = NewsContentFragBinding.inflate(inflater, container, false)
        return mBinding.root
    }

}