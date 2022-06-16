package com.justin.jetpacklearn.Flow.model

/*
 * created by Justin on 2022/6/10
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */

data class NetworkResponse<T>(var code: Int, var msg: String, var data: T, var  fail: Boolean)


