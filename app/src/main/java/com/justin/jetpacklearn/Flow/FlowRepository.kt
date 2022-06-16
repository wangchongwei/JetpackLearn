package com.justin.jetpacklearn.Flow

import com.justin.jetpacklearn.Flow.model.LoginParams
import com.justin.jetpacklearn.Flow.model.User
import com.justin.jetpacklearn.retrofit2.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import com.justin.jetpacklearn.Flow.model.NetworkResponse

/*
 * created by Justin on 2022/4/12
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */

class FlowRepository() {



    suspend fun  markRequest(): Result<Response> {
        return withContext(Dispatchers.IO) {
            println("login success =>")
            Result.Success(Response("justin"))
        }
    }

    suspend fun login(body: LoginParams): Flow<NetworkResponse<User>> {
        return flow {
            emit(RetrofitClient.getApi().login(body))
        }
    }


}