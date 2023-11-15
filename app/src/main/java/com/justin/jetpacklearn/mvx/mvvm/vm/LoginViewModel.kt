package mvx.mvvm.vm

import android.text.Editable
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mvx.mvvm.m.LoginRemoteDataSource
import mvx.mvvm.m.LoginRepository
import mvx.mvvm.m.LoginResult
import mvx.mvvm.m.LoginState

class LoginViewModel() : ViewModel(){

    var loginRepository: LoginRepository = LoginRepository(LoginRemoteDataSource())

    private val _loginStateLivedata = MutableLiveData<LoginState>()
    val loginStateLiveData: LiveData<LoginState> = _loginStateLivedata

    private val _loginResultLiveData:MutableLiveData<LoginResult?> = MutableLiveData<LoginResult?>()
    val loginReesult: LiveData<LoginResult?> = _loginResultLiveData

    /**
     * 登陆状态状态值，默认为未登陆
     */
    private val loginState = LoginState(LoginState.NOT_LOGIN)

    fun login (userName: String, password: String) {
        viewModelScope.launch {
            val result = loginRepository.login(userName, password)
            delay(1000)

            var finalState = _loginStateLivedata.value
            var finalResult = _loginResultLiveData.value
            if (result.isSuccess) {
                val loginResult = result.getOrNull()
                if (loginResult?.successful == true) {
                    // 登陆成功
                    finalResult = loginResult
                    finalState = LoginState(LoginState.LOGIN_SUCCESS)
                } else {
                    // 密码错误
                    finalResult = loginResult
                    finalState = LoginState(LoginState.LOGIN_FAIL)
                }
            } else {
                // 登陆其他异常
                finalResult = null
                finalState = LoginState(LoginState.LOGIN_FAIL)
            }
            _loginStateLivedata.postValue(finalState!!)
            _loginResultLiveData.postValue(finalResult)
        }
    }

    fun onLoginNameTextChange(edit: Editable) {
        loginRepository.user.loginName = edit.toString()
        loginValid()
    }

    fun onPasswordTextChange(edit: Editable) {
        loginRepository.user.password = edit.toString()
        loginValid()
    }


    /**
     * 判断账号、密码是否符合格式
     * 为何使用postValue，而不是setValue
     */
    fun loginValid() {
        GlobalScope.launch {
            withContext(Dispatchers.Default) {
                if (loginRepository.user.isValid()) {
                    loginState.loginState = LoginState.LOGIN_VALID
                } else {
                    loginState.loginState = LoginState.NOT_LOGIN
                }
                _loginStateLivedata.postValue(loginState)
            }
        }
    }

    /**
     * 当点击登陆按钮时
     */
    fun loginBtnOnClick(loginName: String, password: String) {
        _loginStateLivedata.value = LoginState(LoginState.LOGIN_ING)
        login(loginName, password)
    }
}

