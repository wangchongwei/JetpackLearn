package com.justin.jetpacklearn.mvi.m

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justin.jetpacklearn.BaseViewModel
import com.justin.jetpacklearn.Flow.model.User
import com.justin.jetpacklearn.mvi.i.LoginIntent
import com.justin.jetpacklearn.mvi.repository.LoginRepository
import com.justin.jetpacklearn.mvi.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private var repository: LoginRepository): BaseViewModel() {

    val userIntent = Channel<LoginIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState>
        get() = _state


    init {
        handleIntent()
    }


    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow()
                .collect {
                    when(it) {
                        is LoginIntent.Login -> login()
                    }
                }
        }
    }


    private fun login() {
        viewModelScope.launch {
            _state.value = LoginState.Loading
            repository.login("88971028", "")
                .map {
                    _state.value = try{
                        LoginState.UserData(handleResponse(it))
                    }catch (e: Exception) {
                        LoginState.Error(it)
                    }
                }
                .collect {}
        }
    }


}