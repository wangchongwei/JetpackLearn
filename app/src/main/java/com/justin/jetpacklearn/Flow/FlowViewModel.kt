package com.justin.jetpacklearn.Flow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justin.jetpacklearn.Flow.model.LoginParams
import com.justin.jetpacklearn.Flow.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*

/*
 * created by Justin on 2022/4/12
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */

class FlowViewModel(private val repository: FlowRepository) : ViewModel() {

    var userInfo = MutableLiveData<User>()

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            val result = repository.markRequest()
        }
        println("login =>")
    }

    fun login() {
        println("FlowViewModel login => currentThread:" + Thread.currentThread().name)
        GlobalScope.launch{
            var body = LoginParams(false, "9189186758f7d7ff", "", "88971028", "aYRnq14w4UHnjY9zy7JKSYk/QBK9iXtQOjaNn4djkEXhp8KcxPonX7+OXp3Y94mJobWIuvbiNoBP4K4a9R8T37ONV7AG49wUKHhvK7flxPqrgnwvMj6ioGKSwhl42gl0raAHUJst6vOily3rhGwNLshkxQGkyWIUMRdlCFbQBs1czbvWMpPuhX8I4C4vfcscPo/8kvfPmtJFRCQdFhPMnUbPWKdWrAaAK0XU+SC/F/dib6sJotsn3WofdX3mniOyxoR5z9+R7aRc43FCDxM4rWVp44Q0dscssYWLb42jtr7i0AVRs2RQ/1S2f9hVSksGMAgaBEkRydXRlRgSMVekgg==")
            repository.login(body)
                    // flowOn可以指定链式调用上侧的运行线程
                .flowOn(Dispatchers.IO)
                .catch {

                }.map{
                    it.data
                }.collect{
                    // collect 运行线程取决于当前协程的执行线程
                    println("collect threadName:${Thread.currentThread().name}")
                    println("=======> login response")
                    print(it.toString())
                    userInfo.postValue(it)
                }
        }
    }

}

