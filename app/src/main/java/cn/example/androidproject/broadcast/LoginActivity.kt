package cn.example.androidproject.broadcast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.example.androidproject.Util.showToasts
import cn.example.androidproject.databinding.LoginActivityBinding

class LoginActivity : AppCompatActivity() {

    private val mBinding by lazy { LoginActivityBinding.inflate(layoutInflater) }
    private val main = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {
            login.setOnClickListener {
                val account = username.text.toString()
                val password = password.text.toString()
                if (account == "1" && password == "1") {
                    val intent = Intent(main,LoginOffLineActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    main.showToasts("account or passwor is invalid.")
                }
            }
        }
    }
}