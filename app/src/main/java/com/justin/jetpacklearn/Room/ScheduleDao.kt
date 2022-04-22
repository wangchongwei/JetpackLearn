package com.justin.jetpacklearn.Room

import androidx.room.Dao
import androidx.room.Query

/*
 * created by Justin on 2022/4/20
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule ORDER BY arrival_time ASC")
    fun getAll(): List<Schedule>

    @Query("SELECT * FROM schedule WHERE stop_name = :stopName ORDER BY arrival_time ASC")
    fun getByStopName(stopName: String): List<Schedule>
}