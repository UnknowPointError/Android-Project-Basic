package cn.example.androidProject.storage.sqLite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import cn.example.androidProject.R
import cn.example.androidProject.Util.RecyclerView
import cn.example.androidProject.Util.showToasts
import cn.example.androidProject.databinding.DatabaseActivityBinding

class DatabaseActivity : AppCompatActivity() {


    class MyDatabaseHelper(val context: Context, name: String, version: Int) :
        SQLiteOpenHelper(context, name, null, version) {
        private val createBook = "create table Book (" +
                "id integer primary key autoincrement," +
                "author text," +
                "price real," +
                "pages integer," +
                "name text)"
        private val createCategory = "create table Category(" +
                "id integer primary key autoincrement," +
                "category_name text," +
                "category_code integer)"

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(createBook)
            db.execSQL(createCategory)
            context.showToasts("Create Succeeded")
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("drop table if exists Book")
            db.execSQL("drop table if exists Category")
            onCreate(db)
        }
    }

    private val main = this
    private val mBinding by lazy { DatabaseActivityBinding.inflate(layoutInflater) }
    private val dbHelper by lazy { MyDatabaseHelper(main, "BookStore.db", 2) }
    private val prefs by lazy { getSharedPreferences("DataBase", Context.MODE_PRIVATE) }
    private val databaseData: ArrayList<Any> = arrayListOf()
    private val typeData by lazy { mapOf(R.id.textView to "TextView") }
    private var newPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initComponent()
    }

    private fun initComponent() {
        mBinding.apply {
            createDatabase.setOnClickListener { createDataBase() }
            addData.setOnClickListener { addData() }
            delPagesData.setOnClickListener { deletePagesData() }
            delAllData.setOnClickListener { deleteAllData() }
            delLastData.setOnClickListener { deleteLastData() }
            queryData.setOnClickListener { queryData() }
            updateData.setOnClickListener { updateData() }
            recyclerView.layoutManager = LinearLayoutManager(main)
            val adapter = RecyclerView(R.layout.textview_recycler, typeData, databaseData)
            adapter.setHasStableIds(true)
            recyclerView.adapter = adapter
        }
    }


    private fun createDataBase() {
        prefs.apply {
            edit().apply {
                putBoolean("isCreate BookStore", false)
                apply()
            }
            dbHelper.writableDatabase
            if (!getBoolean("isCreate BookStore", true)) {
                main.showToasts("Database has been created.")
            }
        }
    }

    private fun addData() {
        mBinding.apply {
            val db = dbHelper.writableDatabase
            db.beginTransaction()
            try {
                val values1 = ContentValues().apply {
                    put("name", "The Da Vinci Code")
                    put("author", "Dan Brown")
                    put("pages", 300)
                    put("price", 19.95)
                }
                db.insert("Book", null, values1)
                val values2 = ContentValues().apply {
                    put("name", "The Lost Symbol")
                    put("author", "Dan Brown")
                    put("pages", 510)
                    put("price", 19.02)
                }
                db.insert("Book", null, values2)
                queryData()
//                main.showToasts("Data add Successfully")
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction()
            }
        }
    }

    private fun deletePagesData() {
        val db = dbHelper.writableDatabase
        db.beginTransaction()
        try {
            val isDelete = db.delete("Book", "pages > ?", arrayOf("500"))
            if (isDelete != 0)
                main.showToasts("Delete Data Successfully : pages > 500")
            else
                main.showToasts("Delete Data Fail.")
            queryData()
            db.setTransactionSuccessful()

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.endTransaction()
        }
    }

    private fun deleteAllData() {
        val db = dbHelper.writableDatabase
        db.beginTransaction()
        try {
            val isDelete = db.delete("Book", null, null)
            if (isDelete != 0) {
                newPosition = -1
                main.showToasts("Delete AllData Successfully.")
            } else
                main.showToasts("Delete AllData Fail.")
            queryData()
            db.setTransactionSuccessful()

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.endTransaction()
        }
    }

    private fun deleteLastData() {
        val db = dbHelper.writableDatabase
        db.beginTransaction()
        try {
            val isDelete = db.delete("Book", "id < 0", null)
            if (isDelete != 0)
                main.showToasts("Delete Last Data Successfully.")
            else
                main.showToasts("Delete Data Fail.")
            queryData()
            db.setTransactionSuccessful()

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.endTransaction()
        }
    }

    private fun updateData() {
        val db = dbHelper.writableDatabase
        db.beginTransaction()
        try {
            val values = ContentValues()
            values.put("price", "10.99")
            val isUpdate = db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
            newPosition = -1
            queryData()
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        db.endTransaction()
    }

    @SuppressLint("Range")
    private fun queryData() {
        databaseData.clear()
        val db = dbHelper.writableDatabase
        db.beginTransaction()
        try {
            val cursor = db.query("Book", null, null, null, null, null, null)
            cursor.apply {
                if (count != 0 && newPosition != count) {
                    newPosition = count
                    if (moveToFirst()) {
                        do {
                            val name = getString(getColumnIndex("name"))
                            val author = getString(getColumnIndex("author"))
                            val pages = getInt(getColumnIndex("pages"))
                            val price = getDouble(getColumnIndex("price"))
                            databaseData.add("$name   $author   $pages   $price")
                        } while (moveToNext())
                        val adapter =
                            RecyclerView(R.layout.textview_recycler, typeData, databaseData)
                        adapter.setHasStableIds(true)
                        mBinding.recyclerView.adapter = adapter
                    }
                } else if (count == 0) {
                    val adapter =
                        RecyclerView(R.layout.textview_recycler, typeData, databaseData)
                    adapter.setHasStableIds(true)
                    mBinding.recyclerView.adapter = adapter
                } else {
                    main.showToasts("Data is up to data")
                }
                close()
            }
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        db.endTransaction()
    }


}
