package com.justin.jetpacklearn.retrofit2

import okhttp3.Interceptor
import okhttp3.Response

/*
 *
 * created by Justin on 2022/6/9
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */

class LocalInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("appVersion", "9.9.11.9")
            .addHeader("osVersion", "android11")
            .addHeader("skipToken", "true")
            .addHeader("skipSign", "true")
            .addHeader("token", "")
            .addHeader("sign", "")
            .addHeader("isNativeDev", "true")
            .addHeader("loginMode", "0")
            .addHeader("loginTag", "")
            .build()
        return chain.proceed(request)
    }
}