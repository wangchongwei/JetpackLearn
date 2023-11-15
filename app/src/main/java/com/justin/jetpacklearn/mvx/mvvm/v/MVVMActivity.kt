package mvx.mvvm.v

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.dialog.MaterialDialogs
import com.justin.jetpacklearn.R
import com.justin.jetpacklearn.databinding.ActivityMvvmactivityBinding
import mvx.mvvm.m.LoginState
import mvx.mvvm.vm.LoginViewModel

class MVVMActivity : AppCompatActivity() {

    lateinit var mvvmBinding: ActivityMvvmactivityBinding
    val loginViewModel: LoginViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvvmBinding = ActivityMvvmactivityBinding.inflate(layoutInflater)
        setContentView(mvvmBinding.root)

        loginViewModel.loginStateLiveData.observe(this) {
            when (it.loginState) {
                LoginState.LOGIN_ING -> {
                    showDialog()
                }

                LoginState.LOGIN_FAIL, LoginState.LOGIN_SUCCESS -> {
                    dismissDialog()
                }


            }
            loginViewModel.loginValid()
        }

        loginViewModel.loginReesult.observe(this) {
            if (it?.successful == true) {
                showToast("欢迎回来,${it?.userInfo?.userName}")
            }
            if (it?.successful == false) {
                it?.errorMsg?.let { it1 -> showToast(it1) }
            }
        }

        mvvmBinding.loginBtn.setOnClickListener {
            loginViewModel.loginBtnOnClick(mvvmBinding.loginName.text.toString(), mvvmBinding.password.text.toString())
        }


    }

    fun initDialog() {

    }

    fun showDialog() {
        Toast.makeText(this, "登陆中....", Toast.LENGTH_SHORT).show()
    }

    fun dismissDialog() {
        finish()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
