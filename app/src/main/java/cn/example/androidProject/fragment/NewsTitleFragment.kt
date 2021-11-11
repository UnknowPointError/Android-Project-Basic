package cn.example.androidProject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.example.androidProject.R
import cn.example.androidProject.databinding.NewsItemBinding
import cn.example.androidProject.databinding.NewsTitleFragBinding

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidProject/fragment
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 19:14
 * @Day:  星期四
 * @Description:
 **************************/
class NewsTitleFragment : Fragment() {

    inner class NewsAdapter(private val newsList: List<News>, private var isTwoPane: Boolean) :
        RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

        inner class ViewHolder(mBinding: NewsItemBinding) :
            RecyclerView.ViewHolder(mBinding.root) {
            val newsTitle: TextView = mBinding.newsTitle
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val mBinding = NewsItemBinding.inflate(LayoutInflater.from(parent.context))
            val holder = ViewHolder(mBinding)
            holder.itemView.setOnClickListener {
                val news = newsList[holder.adapterPosition]
                isTwoPane = activity?.findViewById<View>(R.id.newsContentFrag) != null
                if (isTwoPane) {
                    val fragment =
                        activity?.supportFragmentManager?.findFragmentById(R.id.newsContentFrag) as NewsContentFragment
                    fragment.refresh(news.title, news.content)
                } else {
                    NewsContentActivity.actionStart(parent.context, news.title, news.content)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val news = newsList[position]
            holder.newsTitle.text = news.title
        }

        override fun getItemCount(): Int = newsList.size
    }

    private var isTwoPane = true
    private lateinit var mBinding: NewsTitleFragBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = NewsTitleFragBinding.inflate(inflater, container, false)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        val adapter = NewsAdapter(getNews(), isTwoPane)
        mBinding.apply {
            newsTitleRecyclerView.layoutManager = layoutManager
            newsTitleRecyclerView.adapter = adapter
        }
    }

    private fun getNews(): List<News> {
        val newList = ArrayList<News>()
        val text = "This is news title"
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