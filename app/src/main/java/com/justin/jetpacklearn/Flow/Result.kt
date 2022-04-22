package com.justin.jetpacklearn.Flow

/*
 * created by Justin on 2022/4/12
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()


}

data class Response(val name: String)