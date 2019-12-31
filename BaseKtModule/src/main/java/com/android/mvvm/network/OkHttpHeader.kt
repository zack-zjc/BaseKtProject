package com.android.mvvm.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Author zack
 * @Date 2019/8/11
 * @Description 请求头部
 * @Version 1.0
 */
class OkHttpHeader: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("accept", "application/x-protobuf")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}