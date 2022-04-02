package com.justin.jetpacklearn.LiveData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.justin.jetpacklearn.R
import com.justin.jetpacklearn.databinding.ActivityLiveDataBinding

class LiveDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLiveDataBinding
    private lateinit var liveData: MutableLiveData<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        liveData = MutableLiveData<String>()

        var observer: Observer<String>  = Observer<String>{
            binding.textView.text = it
        }
        liveData.observe(this, observer)
    }

    override fun onResume() {
        super.onResume()
        liveData.setValue("Hello World!!")
    }
}