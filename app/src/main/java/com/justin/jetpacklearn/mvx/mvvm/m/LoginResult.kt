package mvx.mvvm.m

data class LoginResult(
    var successful: Boolean,
    var errorMsg: String? = "",
    var userInfo: UserInfo? = null
)
