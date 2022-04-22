package com.justin.jetpacklearn.Room

import androidx.lifecycle.ViewModel

/*
 * created by Justin on 2022/4/20
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */

class RoomViewModel(private val scheduleDao: ScheduleDao) : ViewModel() {

    fun fullSchedule(): List<Schedule> = scheduleDao.getAll()

    fun scheduleFromStopName(name: String): List<Schedule> = scheduleDao.getByStopName(name)

}