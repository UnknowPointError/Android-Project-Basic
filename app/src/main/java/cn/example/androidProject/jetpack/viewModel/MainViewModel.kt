package cn.example.androidProject.jetpack.viewModel

import androidx.lifecycle.ViewModel

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/java/cn/example/androidProject/jetpack/viewModel
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/10 14:49 ζζδΈ
 * @Description:
 **************************/
class MainViewModel(countReserved: Int) : ViewModel() {

    var counter = countReserved

}