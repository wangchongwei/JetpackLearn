package com.justin.jetpacklearn.hilt

import com.justin.jetpacklearn.Flow.Response
import com.justin.jetpacklearn.Flow.model.NetworkResponse
import com.justin.jetpacklearn.retrofit2.RetrofitClient
import com.justin.jetpacklearn.retrofit2.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import dagger.internal.InjectedFieldSignature
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

class HiltRepository @Inject constructor(
) {
    suspend fun getUserInfo(userId: String): Flow<NetworkResponse<Object>>{
        return flow<NetworkResponse<Object>> {
            emit(RetrofitClient.getUserApiService().getUserInfo(userId))
        }.flowOn(Dispatchers.IO)
    }
}

@Module
@InstallIn(ActivityComponent::class)
object HiltRepositoryModule{

    @Provides
    fun providerHiltRepository() :HiltRepository = HiltRepository()


}