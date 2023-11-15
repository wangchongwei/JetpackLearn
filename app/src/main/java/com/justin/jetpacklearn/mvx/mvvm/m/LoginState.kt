package mvx.mvvm.m

/**
 * 定义登陆状态枚举值
 */
data class LoginState(
    var loginState: Int = NOT_LOGIN
    ) {
    companion object {
        const val NOT_LOGIN = 0
        const val LOGIN_VALID = 1
        const val LOGIN_ING = 2
        const val LOGIN_FAIL = 3
        const val LOGIN_SUCCESS = 4
    }
}
