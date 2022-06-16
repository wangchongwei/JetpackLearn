package com.justin.jetpacklearn

import android.app.Application
import com.justin.jetpacklearn.Room.AppDatabase
import dagger.hilt.android.HiltAndroidApp

/*
 * created by Justin on 2022/4/22
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */
@HiltAndroidApp
class MyApplication : Application() {


    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }

}