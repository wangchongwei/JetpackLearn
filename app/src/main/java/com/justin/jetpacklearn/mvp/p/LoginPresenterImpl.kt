package com.justin.jetpacklearn.mvp.p

import com.justin.jetpacklearn.Flow.model.NetworkResponse
import com.justin.jetpacklearn.Flow.model.User
import com.justin.jetpacklearn.mvp.m.LoginModel
import com.justin.jetpacklearn.mvp.m.LoginModelImpl
import com.justin.jetpacklearn.mvp.v.IView

class LoginPresenterImpl(private var loginView: IView?) : LoginPresenter, LoginFinishedListener {

    var loginModel: LoginModel? = LoginModelImpl()





    override fun onPhoneNumberError(phoneNumber: String) {
        loginView?.hideProgressBar()
        loginView?.onPhoneNumberError(phoneNumber)
    }

    override fun onLoginFailed(errorResponse: NetworkResponse<User>?) {
        loginView?.hideProgressBar()
        loginView?.loginError(errorResponse)
    }

    override fun onLoginSuccess(user: User) {
        loginView?.hideProgressBar()
        loginView?.loginSuccess(user)
    }

    override fun validatePassword(phoneNumber: String, password: String) {
        loginView?.showProgressBar()
        loginModel?.login(phoneNumber,password, this)
    }

    override fun onDestroy() {
        loginView = null
        loginModel = null
    }
}