package mvx.mvvm.m

import android.text.TextUtils

data class LoginModel(
    var loginName: String,
    var password: String
    ) {

    /**
     * 校验账号、密码格式是否符合规范
     */
    fun isValid(): Boolean {

        return !TextUtils.isEmpty(loginName) && !TextUtils.isEmpty(password) && password.length >= 6 && loginName.length >= 5
    }
}