package cn.example.androidProject.http

interface HttpCallbackListener {
    fun onFinish(response: String)
    fun onError(e: Exception)
}