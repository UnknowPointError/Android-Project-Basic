package cn.example.androidProject.jetpack.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidProject/jetpack/viewModel
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/10 16:52 星期三
 * @Description:
 **************************/
class MainViewModelFactory(private val countReserved: Int) : ViewModelProvider.Factory {
    /**
     * Creates a new instance of the given `Class`.
     *
     *
     *
     * @param modelClass a `Class` whose instance is requested
     * @param <T>        The type parameter for the ViewModel.
     * @return a newly created ViewModel
    </T> */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(countReserved) as T
    }
}