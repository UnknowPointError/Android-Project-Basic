package cn.example.androidProject.jetpack.liveData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import cn.example.androidProject.jetpack.Repository
import cn.example.androidProject.jetpack.User

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidProject/jetpack/viewModel
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/10 14:49 星期三
 * @Description:
 **************************/
class LiveDataMainViewModel(countReserved: Int) : ViewModel() {


    val counter: LiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()
    private val userIdLiveData = MutableLiveData<String>()

    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) { userId ->
        Repository.getUser(userId)
    }

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }

    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }
}