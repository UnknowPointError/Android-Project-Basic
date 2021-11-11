package cn.example.androidProject.jetpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cn.example.androidProject.jetpack.User
import cn.example.androidProject.jetpack.UserDao

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidProject/jetpack/room
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/11 12:26 星期四
 * @Description:
 **************************/

@Database(version = 1, entities = [User::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"app_database")
                .allowMainThreadQueries()
                .build().apply {
                    instance  =this
                }
        }
    }
}