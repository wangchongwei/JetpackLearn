package com.justin.jetpacklearn.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.reactivex.annotations.NonNull

/*
 * created by Justin on 2022/4/20
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */

@Entity
data class Schedule (
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "stop_name") val stopName: String,
    @NonNull @ColumnInfo(name = "arrival_time") val arrivalTime: Int
        )