package com.justin.jetpacklearn.socket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.justin.jetpacklearn.databinding.ActivitySocketBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.io.*
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket

class SocketActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySocketBinding

    private lateinit var socket: Socket

    private lateinit var outputStream: OutputStream
    private lateinit var inputStream: InputStream
    private lateinit var isr: InputStreamReader
    private lateinit var br: BufferedReader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySocketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initView()
    }

    private fun initView() {
        binding.connect.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    socket = Socket("192.168.101.130", 3333)


                    println("connect status => " + socket.isConnected)
                } catch (e: IOException) {
                    e.printStackTrace()
                    println("Socket IOException =>" + e.message)
                }
            }
        }

        binding.disconnect.setOnClickListener {
            if(socket == null) {
                println("Please connect socket first !!!")
                return@setOnClickListener
            }
            try {
                outputStream.close()
                br.close()
                isr.close()
                inputStream.close()
                socket.close()
                println("disconnect!!!")
                println("connect status => " + socket.isConnected)
            }catch (e: IOException) {
                e.printStackTrace()
                println("Socket IOException =>" + e.message)
            }
        }

        binding.send.setOnClickListener {
            lifecycleScope.launch (Dispatchers.IO){
                try {
                    // 1、获取输出流
                    outputStream = socket?.getOutputStream()
                    // 2、写入要提交的数据到流对象中
                    outputStream.write("test socket".toByteArray())
                    // 3、发送数据
                    outputStream.flush()
                }catch (e: Exception) {

                }
            }
        }

        binding.receive.setOnClickListener {
            lifecycleScope.launch() {
                flow<String> {
                    // 1、获取输入流
                    inputStream = socket?.getInputStream()
                    // 2、创建输入流读取器对象 并传入输入流对象
                    // 获取输入的信息
                    isr = InputStreamReader(inputStream)
                    br = BufferedReader(isr)

                    var response = br.readLine()

                    emit(response)
                }.flowOn(Dispatchers.IO)
                    .catch {
                        it.printStackTrace()
                        println("error => ${it}")
                    }
                    .collect {
                        println("getInfo => ${it}")
                    }
            }
        }
    }

    private fun initData() {


    }
}