package cn.example.androidProject.contentProvider

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.LinearLayoutManager
import cn.example.androidProject.R
import cn.example.androidProject.Util
import cn.example.androidProject.Util.showToasts
import cn.example.androidProject.databinding.DataBaseProviderActivityBinding

class DataBaseProviderActivity : AppCompatActivity() {

    private val mBinding by lazy { DataBaseProviderActivityBinding.inflate(layoutInflater) }
    private val dataBase: ArrayList<Any> = arrayListOf()
    private val component: Map<Any, String> = mapOf(R.id.textView to "TextView")
    private val uriContent = "content://cn.example.androidProject.provider/book"
    private var bookId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initComponent()
    }

    private fun initComponent() {
        mBinding.apply {
            queryData.setOnClickListener { query() }
            addData.setOnClickListener { add() }
            updateData.setOnClickListener { update() }
            deleteData.setOnClickListener { delete() }
            deleteAllData.setOnClickListener { deleteAll() }
        }
    }


    private fun recyclerUpdate() {
        val adapter = Util.RecyclerView(R.layout.textview_recycler, component, dataBase)
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerView.adapter = adapter
    }

    @SuppressLint("Range")
    private fun query() {
        dataBase.clear()
        val uri = Uri.parse(uriContent)
        contentResolver.query(uri, null, null, null, null)?.apply {
            while (moveToNext()) {
                val name = getString(getColumnIndex("name"))
                val author = getString(getColumnIndex("author"))
                val pages = getInt(getColumnIndex("pages"))
                val price = getDouble(getColumnIndex("price"))
                dataBase.add("$name   $author   $pages   $price")
            }
            close()
            recyclerUpdate()
        }
        this.showToasts("执行查询：$dataBase")
    }

    private fun add() {
        val uri = Uri.parse(uriContent)
        val values =
            contentValuesOf("name" to "A Clash of Kings",
                "author" to "George Martin",
                "pages" to 1040,
                "price" to 22.85)
        val newUri = contentResolver.insert(uri, values)
        bookId = newUri?.pathSegments?.get(1)
        mBinding.bookId.text = bookId
        this.showToasts("bookId = $bookId")
        query()
        recyclerUpdate()
    }

    private fun update() {
        bookId?.let {
            val uri = Uri.parse("$uriContent/$it")
            val values =
                contentValuesOf("name" to "A Storm of Swords", "pages" to 1216, "price" to 24.05)
            contentResolver.update(uri, values, null, null)
            query()
            recyclerUpdate()
        }
    }

    private fun delete() {
        bookId?.let {
            val uri = Uri.parse("$uriContent/$it")
            contentResolver.delete(uri,null,null)
            query()
            recyclerUpdate()
        }
    }


    private fun deleteAll() {
        val uri = Uri.parse(uriContent)
        contentResolver.delete(uri, null, null)
        dataBase.clear()
        query()
        recyclerUpdate()
    }
}

