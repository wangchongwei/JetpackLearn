package mvx.mvvm.m

import java.util.UUID

/**
 * 通过远端获取登陆的数据
 */
class LoginRemoteDataSource: LoginDataSource {

    override suspend fun login(loginName: String, password: String): Result<LoginResult> {
        try {
            println("账号：${loginName},密码：${password}")
            if (loginName.equals("justin") && password.equals("123456")) {
                return Result.success(LoginResult(true, userInfo = UserInfo(UUID.randomUUID().toString(), "19921206", "justin")))
            }
            return Result.success(LoginResult(false, " 账号密码错误!"))
        } catch (e: Throwable) {
            return Result.failure(e)
        }
    }
}