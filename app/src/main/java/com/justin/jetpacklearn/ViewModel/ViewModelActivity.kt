package com.justin.jetpacklearn.ViewModel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.justin.jetpacklearn.R
import com.justin.jetpacklearn.databinding.ActivityViewModelBinding

class ViewModelActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityViewModelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)

        val model: MyViewModel by viewModels()

        var observer: Observer<List<User>> = Observer {
            println("it => " + it)
        }

        model.getUsers().observe(this, observer)
    }
}