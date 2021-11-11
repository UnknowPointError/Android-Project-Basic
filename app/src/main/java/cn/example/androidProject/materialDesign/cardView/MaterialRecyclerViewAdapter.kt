package cn.example.androidProject.materialDesign.cardView

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.example.androidProject.R
import cn.example.androidProject.databinding.MaterialRecyclerviewItemBinding
import cn.example.androidProject.listView.Fruit
import cn.example.androidProject.materialDesign.collapsingToolBarLayout.CollapsingToolBarActivity
import com.bumptech.glide.Glide

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidProject/materialDesign/cardView
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/10 9:19 星期三
 * @Description:
 **************************/
class MaterialRecyclerViewAdapter(val context: Context, val fruitList: List<Fruit>) :
    RecyclerView.Adapter<MaterialRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(mBinding: MaterialRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        val fruitImage: ImageView = mBinding.fruitImage
        val fruitName: TextView = mBinding.fruitName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mBinding = MaterialRecyclerviewItemBinding.bind(LayoutInflater.from(context)
            .inflate(R.layout.material_recyclerview_item, parent, false))
        val holder = ViewHolder(mBinding)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val fruit = fruitList[position]
            val intent = Intent(context, CollapsingToolBarActivity::class.java).apply {
                putExtra(CollapsingToolBarActivity.FRUIT_NAME, fruit.fruitName)
                putExtra(CollapsingToolBarActivity.FRUIT_IMAGE_ID, fruit.fruitImage)
            }
            context.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitName.text = fruit.fruitName
        Glide.with(context).load(fruit.fruitImage).into(holder.fruitImage)
    }

    override fun getItemCount(): Int = fruitList.size
}