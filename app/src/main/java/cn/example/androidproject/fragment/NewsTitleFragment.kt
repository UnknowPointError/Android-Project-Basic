package cn.example.androidproject.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cn.example.androidproject.databinding.NewsTitleFragBinding
import kotlin.properties.Delegates

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidproject/fragment
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 19:14
 * @Day:  星期四
 * @Description:
 **************************/
class NewsTitleFragment : Fragment() {

    private var isTwoPane by Delegates.notNull<Boolean>()
    private lateinit var mBinding: NewsTitleFragBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = NewsTitleFragBinding.inflate(inflater, container, false)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.isTwoPane = NewsActivity.mmBinding?.newsContentLayout != null
        val layoutManager = LinearLayoutManager(activity)
        val adapter = NewsAdapter(getNews(), isTwoPane)
        mBinding.apply {
            newsTitleRecyclerView.layoutManager = layoutManager
            newsTitleRecyclerView.adapter = adapter
        }
    }

    private fun getNews(): List<News> {
        val newList = ArrayList<News>()
        val text: String = "This is news title"
        for (i in 1..50) {
            val news = News("$text $i", getRandomLengthString("$text $i. "))
            newList.add(news)
        }
        return newList
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }
}