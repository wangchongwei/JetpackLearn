package com.justin.jetpacklearn.Flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
 * created by Justin on 2022/4/12
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */

class FlowViewModel(private val repository: FlowRepository) : ViewModel() {

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            val result = repository.markRequest()
        }
        println("login =>")
    }

}