package com.justin.jetpacklearn.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
 * created by Justin on 2022/4/7
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */

class MyViewModel : ViewModel() {

    private val users: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>().also {
            loadUsers()
        }
    }

    fun getUsers(): LiveData<List<User>> {
        return users
    }

    private fun loadUsers() {
        // TODO: 2022/4/7  
    }
}