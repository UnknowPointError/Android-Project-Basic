package cn.example.androidproject.recyclerview

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.example.androidproject.Util
import cn.example.androidproject.databinding.FruitBinding
import cn.example.androidproject.listview.Fruit

/*************************
 * @Name: Android Project
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/26 15:02
 * @Day:  星期二
 * @Description:
 **************************/
class RecyclerAdapter(
    private val fruitList: List<Fruit>,
    private val itemClick: (Fruit) -> Unit
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    open inner class ViewHolder(val mBinding: FruitBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        val fruitImage = mBinding.fruitImage
        val fruitName = mBinding.fruitText
        val view = mBinding.root
    }


    override fun getItemCount() = fruitList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.apply {
            view.setOnClickListener { itemClick(fruit) }
            fruitImage.setImageResource(fruit.fruitImage)
            fruitName.text = fruit.fruitName
            fruit.id = position
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(mBinding = FruitBinding.inflate(LayoutInflater.from(parent.context)))
}