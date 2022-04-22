package com.justin.jetpacklearn.Flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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


}