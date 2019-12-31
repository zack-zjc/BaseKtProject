package com.android.mvvm.network

import com.android.mvvm.ui.InitProvider
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Author zack
 * @Date 2019/10/10
 * @Description 请求添加请求参数后缀
 * @Version 1.0
 */
class OkHttpUrlSuffix: Interceptor {

    private val packageVersion: String by lazy {
        InitProvider.applicationContext.packageManager.getPackageInfo(InitProvider.applicationContext.packageName,0).versionName
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originUrl = original.url().toString()
        val urlSuffix = "os=android&version=$packageVersion"
        var resultUrl = originUrl
        resultUrl += when {
            originUrl.endsWith("?") -> urlSuffix
            originUrl.contains("?") -> "&$urlSuffix"
            else -> "?$urlSuffix"
        }
        val request = original.newBuilder().url(resultUrl).build()
        return chain.proceed(request)
    }

}