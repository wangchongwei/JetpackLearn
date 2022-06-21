package com.justin.jetpacklearn

import androidx.lifecycle.ViewModel
import com.justin.jetpacklearn.Flow.model.NetworkResponse

open class BaseViewModel : ViewModel() {



    fun<T> handleResponse(response: NetworkResponse<T>): T {
        if(response.code == 200) {
            return response.data
        }
        throw Exception("Network Error!")
    }

}