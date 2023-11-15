package mvx.mvp.v

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.justin.jetpacklearn.Flow.model.NetworkResponse
import com.justin.jetpacklearn.Flow.model.User
import com.justin.jetpacklearn.databinding.ActivityMvpBinding
import mvx.mvp.p.LoginPresenter
import mvx.mvp.p.LoginPresenterImpl

class MvpActivity : AppCompatActivity(), IView {

    lateinit var binding: ActivityMvpBinding
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initPresenter()
    }

    private fun initView() {
        binding.login.setOnClickListener {
            loginPresenter.validatePassword(binding.phoneNumber.text.toString(), binding.password.text.toString())
        }
    }

    private fun initPresenter() {
        loginPresenter = LoginPresenterImpl(this)
    }

    override fun onPhoneNumberError(phoneNumber: String) {
        binding.phoneErrorTips.visibility = View.VISIBLE
        binding.passwordErrorTips.visibility = View.INVISIBLE
    }

    override fun loginError(errorResponse: NetworkResponse<User>?) {
        binding.phoneErrorTips.visibility = View.INVISIBLE
        binding.passwordErrorTips.visibility = View.VISIBLE
    }


    override fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun loginSuccess(user: User) {
        binding.loginInfo.visibility = View.VISIBLE
        binding.loginInfo.text = user.toString()
    }
}