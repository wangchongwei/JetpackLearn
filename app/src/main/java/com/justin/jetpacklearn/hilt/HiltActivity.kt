package com.justin.jetpacklearn.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.justin.jetpacklearn.R
import com.justin.jetpacklearn.databinding.ActivityHiltBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {

    lateinit var binding: ActivityHiltBinding

    private val viewModel: HiltViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHiltBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }


    fun initView() {
        binding.login.setOnClickListener {
            println("login =>")
        }

        println(viewModel)
    }
}