package mvx.mvi.v

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.justin.jetpacklearn.databinding.ActivityMviactivityBinding
import mvx.mvi.i.LoginIntent
import mvx.mvi.m.LoginViewModel
import mvx.mvi.state.LoginState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MVIActivity : AppCompatActivity() {

    lateinit var binding: ActivityMviactivityBinding

    private val viewModel: LoginViewModel by viewModels()

    private var uiStateJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMviactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        observeViewModelState()
    }



    private fun initView() {
        binding.login.setOnClickListener {
            lifecycleScope.launch {
                // send 必须运行在协程
                viewModel.userIntent.send(LoginIntent.Login)
            }
        }
    }

    // 监听状态变更
    private fun observeViewModelState() {
        uiStateJob = lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                when(it) {
                    is LoginState.Idle -> {
                        binding.progressBra.visibility = View.GONE
                    }

                    is LoginState.Loading -> {
                        binding.progressBra.visibility = View.VISIBLE
                    }
                    is LoginState.UserData -> {
                        // 请求成功数据
                        binding.progressBra.visibility = View.GONE
                    }

                    is LoginState.Error -> {
                        binding.progressBra.visibility = View.GONE
                        println("error => ${it.error.code}")
                    }

                }
            }
        }
    }

    override fun onStop() {
        uiStateJob?.cancel()
        super.onStop()
    }
}