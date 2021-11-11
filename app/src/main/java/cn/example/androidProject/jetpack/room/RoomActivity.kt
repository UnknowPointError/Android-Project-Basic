package cn.example.androidProject.jetpack.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import cn.example.androidProject.MyApplication
import cn.example.androidProject.R
import cn.example.androidProject.databinding.JetpackRoomActivityBinding
import cn.example.androidProject.jetpack.User
import cn.example.androidProject.jetpack.UserDao
import cn.example.androidProject.util.Util.showToast
import kotlin.concurrent.thread

class RoomActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao
    private lateinit var user1: User
    private lateinit var user2: User

    private val mBinding by lazy { JetpackRoomActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        MyApplication.context = this
        initComponent()
        userDao = AppDatabase.getDatabase(this).userDao()
        user1 = User("Tom", "Brady", 40)
        user2 = User("Tom", "Hanks", 63)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initComponent() {
        mBinding.apply {
            setSupportActionBar(toolBar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            addData.setOnClickListener { addData() }
            updateData.setOnClickListener { updateData() }
            deleteData.setOnClickListener { deleteData() }
            queryData.setOnClickListener { queryData() }
        }
    }

    private fun addData() {
        thread {
            user1.id = userDao.insertUser(user1)
            user2.id = userDao.insertUser(user2)
        }
    }

    private fun updateData() {
        thread {
            user1.age = 42
            userDao.updateUser(user1)
        }
    }

    private fun deleteData() {
        thread { userDao.deleteUserByLastName("Hanks") }
    }

    private fun queryData() {
        thread {
            var text = ""
            for (user in userDao.loadAllUsers()) {
                text += user
            }
            runOnUiThread {
                showToast(text, true)
            }
        }
    }
}