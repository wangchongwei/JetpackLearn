package com.justin.jetpacklearn.hilt

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HiltViewModel @Inject constructor(
    var repository: HiltRepository,
): ViewModel() {



    fun login() {
        repository.login()
    }

}