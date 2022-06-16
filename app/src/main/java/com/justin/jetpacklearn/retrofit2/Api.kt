package com.justin.jetpacklearn.retrofit2

import com.justin.jetpacklearn.Flow.model.LoginParams
import com.justin.jetpacklearn.Flow.model.NetworkResponse
import com.justin.jetpacklearn.Flow.model.User
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/*
 * created by Justin on 2022/6/9
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */

interface Api {

//    @FormUrlEncoded
    @POST("/api/user/verificationPassword")
    suspend fun login(@Body loginParams: LoginParams) : NetworkResponse<User>


}