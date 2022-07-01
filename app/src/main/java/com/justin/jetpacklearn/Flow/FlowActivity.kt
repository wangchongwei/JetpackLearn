package com.justin.jetpacklearn.Flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.justin.jetpacklearn.databinding.ActivityFlowBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

class FlowActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityFlowBinding
    private lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: FlowViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)
        viewModelFactory = Injection.providerFactory(this)
        
        initView()

        test()
    }

    private fun test() {
        GlobalScope.launch {
            flow<Int> {
                emit(1)
            }
                .map {
                    it.toString()
                }

                .collect {

            }
        }

    }

    private fun initView() {
        dataBinding.login.setOnClickListener {
            viewModel.login()
        }

        viewModel.userInfo.observe(this){
            dataBinding.loginInfo.text = it.toString()
        }

    }

    fun flow1() = runBlocking {
        val time = measureTimeMillis {
            flow {
                for (i in 1..5) {
                    delay(100)
                    emit(i)
                }
            }.collect{
                delay(100)
                println("flow collect => $it")
            }
        }
       println("time => $time")
    }

    fun channelFlow1() = runBlocking {
        val time = measureTimeMillis {
            channelFlow {
                for (i in 1..5) {
                    delay(100)
                    send(i)
                }
            }.collect {
                delay(100)
                println("channelFlow collect => $it")
            }
        }
        println("time => $time")
    }

}

