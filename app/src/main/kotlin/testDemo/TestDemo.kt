package testDemo

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/*************************
 * @ProjectName: Android Project
 * @Dir_Path: app/src/main/kotlin/testDemo
 * @Author: BarryAllen
 * @Time: 2021/11/12 21:54 星期五
 * TODO: Test
 **************************/
class TestDemo

const val RETROFIT_JSON_DATA = "http://120.79.132.118/"
const val HTTP_JSON_DATA = "http://120.79.132.118/get_data.json"

class App(val id: String, val name: String, val version: String)

interface AppService {
    @GET("get_data.json")
    fun getAppData(): Call<List<App>>
}

fun main() {
    val retrofit = Retrofit.Builder()
        .baseUrl(RETROFIT_JSON_DATA)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val appService = retrofit.create(AppService::class.java)
    appService.getAppData().enqueue(object : Callback<List<App>> {
        /**
         * Invoked for a received HTTP response.
         *
         *
         * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
         * Call [Response.isSuccessful] to determine if the response indicates success.
         */
        override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {
            response.body()?.forEach { println("${it.id}\t${it.name}\t${it.version}") }
        }

        /**
         * Invoked when a network exception occurred talking to the server or when an unexpected exception
         * occurred creating the request or processing the response.
         */
        override fun onFailure(call: Call<List<App>>, t: Throwable) {
            TODO("Not yet implemented")
        }

    })
}

