package cn.example.androidProject.broadcast

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.example.androidProject.Util.showToasts
import cn.example.androidProject.databinding.LoginActivityBinding

/*************************
 * @ClassName: LoginActivity.kt
 * @Dir_Path: cn\example\androidProject\broadcast\LoginActivity.kt
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/3 22:29 周三
 * @Description:
 **************************/
class LoginActivity : AppCompatActivity() {

    private val mBinding by lazy { LoginActivityBinding.inflate(layoutInflater) }
    private val main = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        val prefs = getSharedPreferences("RememberPassword", Context.MODE_PRIVATE)
        val isRemember = prefs.getBoolean("isRemember", false)
        if (isRemember) {
            mBinding.apply {
                username.setText(prefs.getString("username", ""))
                password.setText(prefs.getString("password", ""))
                isRememberBox.isChecked = true
            }
        }
        mBinding.apply {
            login.setOnClickListener {
                val account = username.text.toString()
                val password = password.text.toString()
                val user = "admin"
                val pwd = "123"
                if (account == user && password == pwd) {
                    prefs.edit().apply {
                        if (isRememberBox.isChecked) {
                            putString("username", user)
                            putString("password", pwd)
                            putBoolean("isRemember", true)
                        } else {
                            clear()
                        }
                        apply()
                    }
                    val intent = Intent(main, LoginOffLineActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    main.showToasts("account or password is invalid.")
                }
            }
        }
    }
}