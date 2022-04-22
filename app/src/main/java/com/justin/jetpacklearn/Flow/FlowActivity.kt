package com.justin.jetpacklearn.Flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.justin.jetpacklearn.databinding.ActivityFlowBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.channelFlow

class FlowActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityFlowBinding
    private lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: FlowViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)
        viewModelFactory = Injection.providerFactory(this)
        flow1()
        channelFlow1()
        
        initView()
    }

    private fun initView() {
        dataBinding.login.setOnClickListener {
            val result = viewModel.login("user", "123321qQ")
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

