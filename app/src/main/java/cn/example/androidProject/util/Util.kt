package cn.example.androidProject.util

import android.app.ActivityManager
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import cn.example.androidProject.MyApplication
import com.google.android.material.snackbar.Snackbar

/*************************
 * @Name: Android Project
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/10/25 14:02
 * @Day:  星期一
 * @Description:
 **************************/
object Util {

    class TestException(message: String) : Exception(message)
    class RecyclerView<T>(
        val res: Int,
        private val component: Map<T, String>,
        var data: ArrayList<Any>,
    ) : Adapter<RecyclerView<T>.ViewHolder>() {

        private var i = 0

        inner class ViewHolder(view: View, component: Map<T, String>) :
            androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
            var componentTotal: MutableMap<String, Any> = mutableMapOf("" to "")

            init {
                component.forEach {
                    when (it.value) {
                        "TextView" -> {
                            componentTotal["TextView"] = view.findViewById(it.key as Int)
                        }
                        "ImageView" -> {
                            componentTotal["ImageView"] = view.findViewById(it.key as Int)
                        }
                    }
                }
            }
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.componentTotal.forEach {
                when (it.key) {
                    "TextView" -> {
                        (it.value as TextView).text = data[position].toString()
                    }
                    "ImageView" -> {
                        (it.value as ImageView).setImageResource(data[position] as Int)
                    }
                }
            }
        }


        override fun getItemCount() = data.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(res, parent, false)
            return ViewHolder(view, component)
        }
    }

    private var toast: Toast? = null
    private lateinit var resources: Resources


    /*************************
     * @Name: showToast
     * @Params: [text]
     * @Author: BarryAllen
     * @Description:
     * @DateTime: 2021/11/4 17:11
     **************************/
    fun showToast(text: String, displayTimeMode: Boolean = false) {
        if (toast != null)
            (toast ?: return).cancel()// 当toast为null时返回冒号后面的值，只有return则退出
        toast = if (!displayTimeMode)
            Toast.makeText(MyApplication.context, text, Toast.LENGTH_SHORT)
        else
            Toast.makeText(MyApplication.context, text, Toast.LENGTH_LONG)
        toast?.show()
    }

    /*************************
     * @Name: isTabletDevice
     * @Params: [context]
     * @Author: BarryAllen
     * @Description:true:平板,false:手机
     * @DateTime: 2021/9/26 15:49
     **************************/
    fun isTabletDevice(context: Context): Boolean =
        context.resources
            .configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >=
                Configuration.SCREENLAYOUT_SIZE_LARGE

    /*************************
     * @Name: multiplication
     * @Params: [count]
     * @Author: BarryAllen
     * @Description:
     * @DateTime: 2021/10/13 11:01
     **************************/
    fun String.multiplication(count: Int): String {
        return when {
            count < 1 ->
                throw TestException("The count parameter of the string multiplication function cannot be less than or equal to 0")
            count == 1 -> this
            else -> {
                var text = ""
                for (i in 1 until count) text += this + "\n"
                text
            }
        }
    }


    /*************************
     * @Name: isScreenChange
     * @Params: [resources]
     * @Author: BarryAllen
     * @Description:true 横屏、false 竖屏
     * @DateTime: 2021/10/15 10:29
     **************************/
    fun isScreenChange(): Boolean {
        val mConfiguration by lazy {
            resources.configuration //获取设置的配置信息
        }
        val ori = mConfiguration.orientation //获取屏幕方向
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
            return true
        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {
            return false
        }
        return false
    }


    /*************************
     * @Name: isServiceRunning
     * @Params: [context, serviceName]
     * @Author: BarryAllen
     * @Description:
     * @DateTime: 2021/11/4 17:12
     **************************/
    fun isServiceRunning(context: Context, serviceName: String): Boolean {
        // 校验服务是否还存在
        val am = context
            .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val services: List<ActivityManager.RunningServiceInfo> = am.getRunningServices(100)
        for (info in services) {
            // 得到所有正在运行的服务的名称
            val name: String = info.service.className
            if (serviceName == name) {
                return true
            }
            Log.d("Services", "name = $name")
        }
        return false
    }


    /*************************
     * @Name: typename
     * @Params: []
     * @Author: BarryAllen
     * @Description:
     * @DateTime: 2021/11/4 17:12
     **************************/
    fun Any.typename(): String = this::class.java.simpleName

    fun View.showSnackbar(
        text: String,
        actionText: String? = null,
        duration: Int = Snackbar.LENGTH_LONG,
        block: (() -> Unit)? = null,
    ) {
        val snackbar = Snackbar.make(this, text, duration)
        if (actionText != null && block != null) {
            snackbar.setAction(actionText) {
                block()
            }
        }
        snackbar.show()
    }


    fun View.showSnackbar(
        resId: Int,
        actionResId: Int? = null,
        duration: Int = Snackbar.LENGTH_LONG,
        block: (() -> Unit)? = null,
    ) {
        val snackbar = Snackbar.make(this, resId, duration)
        if (actionResId != null && block != null) {
            snackbar.setAction(actionResId) {
                block()
            }
        }
        snackbar.show()
    }
}




