package com.justin.jetpacklearn.Flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/*
 * created by Justin on 2022/4/19
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */

class ViewModelFactory(private val repository: FlowRepository): ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FlowViewModel::class.java)) {
            return FlowViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}