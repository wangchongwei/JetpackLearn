package mvx.mvi.repository

import com.justin.jetpacklearn.Flow.model.LoginParams
import com.justin.jetpacklearn.Flow.model.NetworkResponse
import com.justin.jetpacklearn.Flow.model.User
import com.justin.jetpacklearn.retrofit2.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepository @Inject constructor() {


    suspend fun login(userName: String, password: String): Flow<NetworkResponse<User>> {
        return flow {
            emit(RetrofitClient.getApi().login(LoginParams(false, "9189186758f7d7ff", "", "88971028", "aYRnq14w4UHnjY9zy7JKSYk/QBK9iXtQOjaNn4djkEXhp8KcxPonX7+OXp3Y94mJobWIuvbiNoBP4K4a9R8T37ONV7AG49wUKHhvK7flxPqrgnwvMj6ioGKSwhl42gl0raAHUJst6vOily3rhGwNLshkxQGkyWIUMRdlCFbQBs1czbvWMpPuhX8I4C4vfcscPo/8kvfPmtJFRCQdFhPMnUbPWKdWrAaAK0XU+SC/F/dib6sJotsn3WofdX3mniOyxoR5z9+R7aRc43FCDxM4rWVp44Q0dscssYWLb42jtr7i0AVRs2RQ/1S2f9hVSksGMAgaBEkRydXRlRgSMVekgg==")))
        }.flowOn(Dispatchers.IO)
    }

}