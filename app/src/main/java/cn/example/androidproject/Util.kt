package cn.example.androidproject

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.util.Log
import android.widget.Toast

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

    private var toast: Toast? = null
    lateinit var resources: Resources

    fun showToast(context: Context, text: String) {
        if (toast?.equals(null) == false)
            (toast ?: return).cancel()//当toast为null时返回冒号后面的值，只有return则退出
        toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast?.show()
    }

    fun Context.showToasts(text: String) {
        val context: Context = this as Context
        if (toast?.equals(null) == false)
            (toast ?: return).cancel()//当toast为null时返回冒号后面的值，只有return则退出
        toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
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
     * @Name: multip
     * @Params: [count]
     * @Author: BarryAllen
     * @Description:
     * @DateTime: 2021/10/13 11:01
     **************************/
    fun String.multip(count: Int): String {
        return when {
            count < 1 -> {
                throw TestException("The count parameter of the string multip function cannot be less than or equal to 0")
            }
            count == 1 -> {
                this
            }
            else -> {
                var text = ""
                for (i in 1 until count) {
                    text += this
                }
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
            this.resources.configuration //获取设置的配置信息
        }
        val ori = mConfiguration.orientation //获取屏幕方向
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
            return true
        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {
            return false
        }
        return false
    }

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

    fun Any.typename(): String = "${this::class.java.name}"
}




