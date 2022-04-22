package com.justin.jetpacklearn.Flow

import android.content.Context

/*
 * created by Justin on 2022/4/19
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */

object Injection {

    fun provideFlowRepository(context: Context) : FlowRepository {
        return FlowRepository()
    }

    fun providerFactory(context: Context): ViewModelFactory {
        val repositiry = Injection.provideFlowRepository(context);
        return ViewModelFactory(repositiry)
    }


}