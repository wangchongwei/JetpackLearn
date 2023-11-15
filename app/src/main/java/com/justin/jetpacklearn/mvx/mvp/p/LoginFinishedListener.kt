package mvx.mvp.p

import com.justin.jetpacklearn.Flow.model.NetworkResponse
import com.justin.jetpacklearn.Flow.model.User

interface LoginFinishedListener {

    fun onPhoneNumberError(phoneNumber: String)

    fun onLoginFailed(errorResponse: NetworkResponse<User>?)

    fun onLoginSuccess(user: User)

}