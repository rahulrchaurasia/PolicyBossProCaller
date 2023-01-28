package com.example.jetpackdemo

import com.example.jetpackdemo.LoginModule.API.APIService

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val BASE_URL = "https://horizon.policyboss.com:5443"

    const val token = "1234567890"
    internal var restAdapter: Retrofit? = null


    //region Commented
//    fun getInstance() : Retrofit {
//
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//    }

    //endregion


    private val retrofitInstance by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    val retrofitCallerApi by lazy {
        retrofitInstance.create(APIService::class.java)

    }


}