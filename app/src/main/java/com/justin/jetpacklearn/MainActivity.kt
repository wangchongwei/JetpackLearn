package com.justin.jetpacklearn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.justin.jetpacklearn.LiveData.LiveDataActivity
import com.justin.jetpacklearn.ViewModel.ViewModelActivity
import com.justin.jetpacklearn.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
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
    }
}