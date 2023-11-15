package mvx.mvvm.m

import java.util.concurrent.Flow

/**
 * 获取数据仓库
 */
class LoginRepository(var loginDataSource: LoginDataSource) {

    val user: LoginModel by lazy {
        LoginModel("", "")
    }

    suspend fun login(loginName: String, password: String): Result<LoginResult> {
        return loginDataSource.login(loginName, password)
    }
}