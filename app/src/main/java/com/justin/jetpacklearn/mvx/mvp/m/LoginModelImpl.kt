package mvx.mvp.m

import com.justin.jetpacklearn.Flow.model.LoginParams
import mvx.mvp.p.LoginFinishedListener
import com.justin.jetpacklearn.retrofit2.RetrofitClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginModelImpl(): LoginModel {
    override fun login(phoneNumber: String, password: String, listener: LoginFinishedListener) {
        if(phoneNumber.isBlank() || phoneNumber.isEmpty() || phoneNumber.length < 8) {
            listener.onPhoneNumberError(phoneNumber)
            return@login
        }
        GlobalScope.launch {
            try {
                var response = RetrofitClient.getApi().login(LoginParams(false, "9189186758f7d7ff", "", "88971028", "aYRnq14w4UHnjY9zy7JKSYk/QBK9iXtQOjaNn4djkEXhp8KcxPonX7+OXp3Y94mJobWIuvbiNoBP4K4a9R8T37ONV7AG49wUKHhvK7flxPqrgnwvMj6ioGKSwhl42gl0raAHUJst6vOily3rhGwNLshkxQGkyWIUMRdlCFbQBs1czbvWMpPuhX8I4C4vfcscPo/8kvfPmtJFRCQdFhPMnUbPWKdWrAaAK0XU+SC/F/dib6sJotsn3WofdX3mniOyxoR5z9+R7aRc43FCDxM4rWVp44Q0dscssYWLb42jtr7i0AVRs2RQ/1S2f9hVSksGMAgaBEkRydXRlRgSMVekgg=="))
                println("response => ${response}")
                println("code => ${response.code}")
                if(response.code == 200) {
                    listener.onLoginSuccess(response.data)
                } else {
                    listener.onLoginFailed(response)
                }
            }catch (e: Exception) {
                listener.onLoginFailed(null)
            }

        }

    }
}