package com.justin.jetpacklearn.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import dagger.internal.InjectedFieldSignature
import javax.inject.Inject
import javax.inject.Singleton

class HiltRepository @Inject constructor() {

    fun login(){

    }



}

@Module
@InstallIn(ActivityComponent::class)
object HiltRepositoryModule{

    @Provides
    fun providerHiltRepository() :HiltRepository = HiltRepository()
}