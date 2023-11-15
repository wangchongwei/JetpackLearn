package mvx.mvvm.m

import java.util.concurrent.Flow

interface LoginDataSource {

    suspend fun login(loginName: String, password: String): Result<LoginResult>
}