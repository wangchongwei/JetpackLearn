package com.justin.jetpacklearn.mvp.m

import com.justin.jetpacklearn.mvp.p.LoginFinishedListener

interface LoginModel {


    fun login(phoneNumber: String, password: String, listener: LoginFinishedListener)

}