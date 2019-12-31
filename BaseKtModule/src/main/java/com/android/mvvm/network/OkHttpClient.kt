package com.android.mvvm.network

import okhttp3.ConnectionPool
import okhttp3.Protocol
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @Author zack
 * @Date 2019/7/25
 * @Description okHttp的client
 * @Version 1.0
 */
enum class OkHttpClient(followRedirect:Boolean,fileClient: Boolean) {

    //非重定向普通请求client
    REQUEST_CLIENT(false,false),

    //重定向请求client支持redirect
    REQUEST_REDIRECT_CLIENT(true,false),

    //文件非重定向普通请求client
    REQUEST_FILE_CLIENT(false,true),

    //文件重定向请求client支持redirect
    REQUEST_FILE_REDIRECT_CLIENT(true,true);

    //httpClient实例
    private val okHttpClient: okhttp3.OkHttpClient

    init {
        val builder = okhttp3.OkHttpClient.Builder()
            .readTimeout(if (fileClient) 30000L else 15000L, TimeUnit.MILLISECONDS)
            .writeTimeout(if (fileClient) 30000L else 15000L, TimeUnit.MILLISECONDS)
            .connectTimeout(if (fileClient) 30000L else 15000L, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .protocols(listOf(Protocol.HTTP_2, Protocol.HTTP_1_1))
            .connectionPool(ConnectionPool(5, 15, TimeUnit.SECONDS))
            .followRedirects(followRedirect)
            .followSslRedirects(followRedirect)
        builder.addInterceptor(OkHttpHeader())
        builder.addInterceptor(OkHttpUrlSuffix())
        okHttpClient = builder.build()
    }

    /**
     * 获取okHttpClient实例
     */
    fun getOkHttpClient() : okhttp3.OkHttpClient = okHttpClient

}