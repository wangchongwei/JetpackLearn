package com.justin.jetpacklearn.mvp.p


interface LoginPresenter {


    fun validatePassword(phoneNumber: String, password: String)

    fun onDestroy()


}