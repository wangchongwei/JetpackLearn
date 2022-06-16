package com.justin.jetpacklearn.Flow.model

/*
 * created by Justin on 2022/6/9
 * email: wcw1992yu@163.com
 * github: https://github.com/wangchongwei
 */

data class User(
    var token: String,
    var version: String? = null,
    var urlSignSalt: String? = null,
    var userId: String? = null,
    var userName: String? = null,
    val email: String? = null,
    val activePeriod: Int = 0,
    val status: Int = 0,
    val passwordToken: String? = null,
    val remainingTrial: Int = 0,
    val loanAccountId: String? = null,
    val increaseLimit: String? = null,
    val createDate: Long = 0,
    val serverCurrentTime: Long =0,
    val contractSource: Int = 0,
    val contractStatus: Int = 0,
    val couponType: Int = 0,
    val mgmState: Int = 0,
    val mgmAccountState: Int = 0,
    val delayState: Int = 0,

    val endFlag: Int = 0,

    val comitDelayConfirmFlag: Int = 0,

    val trytryUserStatus: Int = 0,
    val phone: String = "",
    val mobile: String = "",

    val loanRequestNo: String = "",

    val contractId: String = "",
    val allowLogin: Boolean = false,
    val userStatus: Int = 0,
    val withdrawFlag: Int = 0,val loginTag: String = "",val referralCode: String? =null

)
