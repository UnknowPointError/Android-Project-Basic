package cn.example.androidProject.contentProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.net.Uri
import cn.example.androidProject.storage.sqLite.DatabaseActivity.MyDatabaseHelper

class DataBaseProvider : ContentProvider() {

    private val bookDir = 0
    private val bookItem = 1
    private val categoryDir = 2
    private val categoryItem = 3
    private val authority = "cn.example.androidProject.provider"
    private var dbHelper: MyDatabaseHelper? = null
    private val uriMatcher by lazy {
        val matcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(authority, "book", bookDir)
            addURI(authority, "book/#", bookItem)
            addURI(authority, "category", categoryDir)
            addURI(authority, "category/#", categoryItem)
        }
        matcher
    }

    override fun onCreate() = context?.let {
        dbHelper = MyDatabaseHelper(it, "BookStore.db", 2)
        true
    } ?: false

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?) =
        dbHelper?.let {
            val db = it.writableDatabase
            val uriDeleteRows = when (uriMatcher.match(uri)) {
                bookDir -> db.delete("Book", selection, selectionArgs)
                bookItem -> {
                    val bookId = uri.pathSegments[1]
                    db.delete("Book", "id = ?", arrayOf(bookId))
                }
                categoryDir -> db.delete("Category", selection, selectionArgs)
                categoryItem -> {
                    val categoryId = uri.pathSegments[1]
                    db.delete("Category", "id = ?", arrayOf(categoryId))
                }
                else -> 0
            }
            uriDeleteRows
        } ?: 0

    override fun getType(uri: Uri) = when (uriMatcher.match(uri)) {
        bookDir -> "vnd.android.cursor.dir/vnd.cn.example.androidProject.provider.book"
        bookItem -> "vnd.android.cursor.item/vnd.cn.example.androidProject.provider.book"
        categoryDir -> "vnd.android.cursor.dir/vnd.cn.example.androidProject.provider.category"
        categoryItem -> "vnd.android.cursor.item/vnd.cn.example.androidProject.provider.category"
        else -> null
    }

    override fun insert(uri: Uri, values: ContentValues?) = dbHelper?.let {
        val db = it.writableDatabase
        val uriReturn = when (uriMatcher.match(uri)) {
            bookDir, bookItem -> {
                val newBookId = db.insert("Book", null, values)
                Uri.parse("content://$authority/book/$newBookId")
            }
            categoryDir, categoryItem -> {
                val newCategoryId = db.insert("Category", null, values)
                Uri.parse("content://$authority/category/$newCategoryId")
            }
            else -> null
        }
        uriReturn
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?,
    ) = dbHelper?.let {
        val db = it.readableDatabase
        val cursor = when (uriMatcher.match(uri)) {
            bookDir -> db.query("Book", projection, selection, selectionArgs, null, null, sortOrder)
            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.query("Book", projection, "id = ?", arrayOf(bookId), null, null, sortOrder)
            }
            categoryDir -> {
                db.query("Category", projection, selection, selectionArgs, null, null, sortOrder)
            }
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.query("Category",
                    projection,
                    "id = ?",
                    arrayOf(categoryId),
                    null,
                    null,
                    sortOrder)
            }
            else -> null
        }
        cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?,
    ) = dbHelper?.let {
        val db = it.writableDatabase
        val uriUpdateRows = when (uriMatcher.match(uri)) {
            bookDir -> db.update("Book", values, selection, selectionArgs)
            bookItem -> {
                val bookID = uri.pathSegments[1]
                db.update("Book", values, "id = ?", arrayOf(bookID))
            }

            categoryDir -> db.update("Category", values, selection, selectionArgs)
            categoryItem -> {
                val categoryID = uri.pathSegments[1]
                db.update("Category", values, "id = ?", arrayOf(categoryID))
            }
            else -> 0
        }
        uriUpdateRows
    } ?: 0
}