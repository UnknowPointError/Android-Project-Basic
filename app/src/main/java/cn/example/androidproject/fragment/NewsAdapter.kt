package cn.example.androidproject.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.example.androidproject.databinding.NewsItemBinding

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidproject/fragment
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/28 23:47
 * @Day:  星期四
 * @Description:
 **************************/
class NewsAdapter(private val newsList: List<News>, private val isTwoPane: Boolean) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(mBinding: NewsItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        val newsTitle: TextView = mBinding.newsTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mBinding = NewsItemBinding.inflate(LayoutInflater.from(parent.context))
        var holder = ViewHolder(mBinding)
        holder.itemView.setOnClickListener {
            val news = newsList[holder.adapterPosition]
            if (isTwoPane) {
                NewsContentFragment.refresh(news.title,news.content)
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