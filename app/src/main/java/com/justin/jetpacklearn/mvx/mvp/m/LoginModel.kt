package mvx.mvp.m

import mvx.mvp.p.LoginFinishedListener

interface LoginModel {


    fun login(phoneNumber: String, password: String, listener: LoginFinishedListener)

}