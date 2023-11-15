package mvx.mvp.v

import com.justin.jetpacklearn.Flow.model.NetworkResponse
import com.justin.jetpacklearn.Flow.model.User

interface IView {

    fun onPhoneNumberError(phoneNumber: String)

    fun loginError(errorResponse: NetworkResponse<User>?)

    fun showProgressBar()

    fun hideProgressBar()

    fun loginSuccess(user: User)


}