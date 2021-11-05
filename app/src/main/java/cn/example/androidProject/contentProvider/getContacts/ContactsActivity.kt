package cn.example.androidProject.contentProvider.getContacts

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import cn.example.androidProject.R
import cn.example.androidProject.Util
import cn.example.androidProject.Util.showToasts
import cn.example.androidProject.databinding.ContactsActivityBinding

class ContactsActivity : AppCompatActivity() {

    private val mBinding by lazy { ContactsActivityBinding.inflate(layoutInflater) }
    private val main = this
    private val dataMap = mutableMapOf(R.id.textView to "TextView")
    private val contactsList = ArrayList<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        if (ContextCompat.checkSelfPermission(main,
                android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(main,
                arrayOf(android.Manifest.permission.READ_CONTACTS),
                1)
        } else {
            readContacts()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts()
                } else {
                    main.showToasts("You denied the permission")
                }
            }
        }
    }

    @SuppressLint("Range")
    private fun readContacts() {
        // 查询联系人
        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null)?.apply {
            while (moveToNext()) {
                val displayName =
                    getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number =
                    getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactsList.add("$displayName\t$number")
            }
            close()
        }
        mBinding.apply {
            val layoutManager = LinearLayoutManager(main)
            val adapter = Util.RecyclerView(R.layout.textview_recycler, dataMap, contactsList)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
        }
    }
}