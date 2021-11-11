package cn.example.androidProject.jetpack

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(val firstName: String, val lastName: String, var age: Int) {


    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
