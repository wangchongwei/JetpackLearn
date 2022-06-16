package com.justin.jetpacklearn.Flow.model

/*
 * created by Justin on 2022/6/9
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */

data class LoginParams(
    var checkNewDevice: Boolean,
    var deviceId: String,
    var firebaseToken: String,
    var phone: String,
    var password: String,

)
