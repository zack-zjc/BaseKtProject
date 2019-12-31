package com.android.mvvm.network

import retrofit2.Retrofit

/**
 * @Author zack
 * @Date 2019/7/25
 * @Description Retrofit的对象
 * @Version 1.0
 */
object RetrofitModel {

    val redirectRetrofit :Retrofit by lazy {
        Retrofit.Builder()
            .client(OkHttpClient.REQUEST_REDIRECT_CLIENT.getOkHttpClient())
            .build()
    }

    val directRetrofit :Retrofit by lazy {
        Retrofit.Builder()
            .client(OkHttpClient.REQUEST_CLIENT.getOkHttpClient())
            .build()
    }

    val fileRetrofit :Retrofit by lazy {
        Retrofit.Builder()
            .client(OkHttpClient.REQUEST_FILE_CLIENT.getOkHttpClient())
            .build()
    }

}