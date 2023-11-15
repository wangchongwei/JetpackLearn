package mvx.mvi.state

import com.justin.jetpacklearn.Flow.model.NetworkResponse
import com.justin.jetpacklearn.Flow.model.User

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class UserData(val user: User) : LoginState()
    data class Error(val error: NetworkResponse<User>) : LoginState()
}
