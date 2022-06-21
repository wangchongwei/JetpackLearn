package com.justin.jetpacklearn.retrofit2

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
 * created by Justin on 2022/6/9
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */

object RetrofitClient {

    private var client = OkHttpClient.Builder()
        .addInterceptor(LocalInterceptor())
        .build()

    private val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://xwallet-selftest.zerofinance.hk")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApi(): Api {
        return instance.create(Api::class.java)
    }

    fun getUserApiService(): UserApi {
        return instance.create(UserApi::class.java)
    }
}