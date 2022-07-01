package com.justin.jetpacklearn

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.justin.jetpacklearn.Flow.FlowActivity
import com.justin.jetpacklearn.LiveData.LiveDataActivity
import com.justin.jetpacklearn.Room.RoomActivity
import com.justin.jetpacklearn.ViewModel.ViewModelActivity
import com.justin.jetpacklearn.databinding.ActivityMainBinding
import com.justin.jetpacklearn.hilt.HiltActivity
import com.justin.jetpacklearn.io.IOActivity
import com.justin.jetpacklearn.message.MessageActivity
import com.justin.jetpacklearn.mvi.v.MVIActivity
import com.justin.jetpacklearn.mvp.v.MvpActivity
import com.justin.jetpacklearn.ui.UIActivity
import java.security.Permission

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

        requestPermission()
    }

    private fun requestPermission() {
        if(checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            var permissionArray = arrayOf(
                Manifest.permission.INTERNET
            )
            requestPermissions(permissionArray, 100)
        }
    }

    private fun initView() {
        binding.livedata.setOnClickListener{
            println("JumpLiveDataActivity ===>")
            startActivity(Intent(this, LiveDataActivity::class.java))
        }
        binding.viewModel.setOnClickListener{
            println("JumpViewModelActivity ===>")
            startActivity(Intent(this, ViewModelActivity::class.java))
        }
        binding.flow.setOnClickListener{
            println("JumpFlowActivity ===>")
            startActivity(Intent(this, FlowActivity::class.java))
        }
        binding.room.setOnClickListener {
            println("JumpRoomActivity ===>")
            startActivity(Intent(this, RoomActivity::class.java))
        }

        binding.hilt.setOnClickListener {
            println("HiltActivity ===>")
            startActivity(Intent(this, HiltActivity::class.java))
        }

        binding.mvi.setOnClickListener {
            println("MVIActivity ===>")
            startActivity(Intent(this, MVIActivity::class.java))
        }

        binding.mvp.setOnClickListener {
            println("MVPActivity ===>")
            startActivity(Intent(this, MvpActivity::class.java))
        }

        binding.io.setOnClickListener {
            startActivity(Intent(this, IOActivity::class.java))
        }

        binding.message.setOnClickListener {
            startActivity(Intent(this, MessageActivity::class.java))
        }

        binding.ui.setOnClickListener {
            startActivity(Intent(this, UIActivity::class.java))
        }
    }
}