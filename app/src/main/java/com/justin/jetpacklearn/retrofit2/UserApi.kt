package com.justin.jetpacklearn.retrofit2

import com.justin.jetpacklearn.Flow.model.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

interface UserApi {

    @POST("/api/user/getUserInformation")
    suspend fun getUserInfo(@Query("userId") userId: String): NetworkResponse<Object>
}