package com.justin.jetpacklearn.Room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/*
 * created by Justin on 2022/4/20
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */

class RoomViewModelFactory(private val scheduleDao: ScheduleDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RoomViewModel::class.java)) {
            return RoomViewModel(scheduleDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}