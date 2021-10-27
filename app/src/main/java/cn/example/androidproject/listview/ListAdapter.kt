package cn.example.androidproject.listview

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import cn.example.androidproject.databinding.FruitBinding

/*************************
 * @Name: Android Project
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/25 19:18
 * @Day:  星期一
 * @Description:
 **************************/
/* 404编写
private val itemCLick: (Fruit) -> Unit
 将此高阶函数放入主钩造中，并在getView中加入点击事件
 view.setOnClickListener {
    itemCLick(fruit)
 }
*/
class ListAdapter(activity: Activity, resId: Int, private val fruitList: List<Fruit>) :
    ArrayAdapter<Fruit>(activity, resId, fruitList) {

    private lateinit var mBinding: FruitBinding

    class ViewHolder(val fruitImage: ImageView, val fruitName: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            mBinding = FruitBinding.inflate(LayoutInflater.from(context))
            view = mBinding.root
            viewHolder = ViewHolder(mBinding.fruitImage, mBinding.fruitText)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val fruit = getItem(position) as Fruit

        viewHolder.fruitImage.setImageResource(fruit.fruitImage)
        viewHolder.fruitName.text = fruit.fruitName
        return view
    }

    override fun getCount() = fruitList.size

}