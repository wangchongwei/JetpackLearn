package mvx.mvp.p

import com.justin.jetpacklearn.Flow.model.NetworkResponse
import com.justin.jetpacklearn.Flow.model.User
import mvx.mvp.m.LoginModel
import mvx.mvp.m.LoginModelImpl
import mvx.mvp.v.IView

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