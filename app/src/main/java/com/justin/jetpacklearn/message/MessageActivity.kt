package com.justin.jetpacklearn.message

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.justin.jetpacklearn.databinding.ActivityMessageBinding
import java.util.*

class MessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessageBinding


    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
    }

    private fun initView() {

        binding.sendMessage.setOnClickListener {
            var message = Message.obtain()
            message.what = 100
            message.setAsynchronous(true)
            handler.sendMessage(message)
            println("sendMessage -> start, time = " + Date().time)
        }

        binding.sendMessageAtTime.setOnClickListener {
            var message = Message.obtain()
            message.what = 200
            handler.sendMessageAtTime(message, SystemClock.uptimeMillis() + 1000)
            println("sendMessageAtTime -> start, time = " + Date().time)
        }

        binding.sendMessageDelayed.setOnClickListener {
            var message = Message.obtain()
            message.what = 300
            handler.sendMessageDelayed(message, 2000)
            println("sendMessageDelayed -> start, time = " + Date().time)
        }

        binding.sendMessageAtFrontOfQueue.setOnClickListener {
            var message = Message.obtain()
            message.what = 400
            handler.sendMessageAtFrontOfQueue(message)
            println("sendMessageAtFrontOfQueue -> start, time = " + Date().time)
        }
    }


    private fun initData() {
        handler = Handler(){
            println("message =>>>>>> " + it.what + ",  time = " + Date().time)
            true
        }

        handler.removeCallbacksAndMessages(null)

    }




}