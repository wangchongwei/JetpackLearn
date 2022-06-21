package com.justin.jetpacklearn.hilt

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HiltViewModel @Inject constructor(
    var repository: HiltRepository,
): ViewModel() {



    fun getUserInfo(userId: String) {
        viewModelScope.launch {
            repository.getUserInfo(userId)
                .map {
                    println("response => ${it}")
                    println("code == 200 => ${it.code == 200}")
                    if(it.code == 200) it.data
                    else throw Exception("Network Error! ${it}")
                }
                .catch {
                    it.printStackTrace()
                }
                .collect {
                    println("getUserInfo =>${it}")
                }
        }
    }

}