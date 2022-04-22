package com.justin.jetpacklearn.Room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.justin.jetpacklearn.MyApplication
import com.justin.jetpacklearn.Room.adapter.BusStopAdapter
import com.justin.jetpacklearn.databinding.ActivityRoomBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class RoomActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityRoomBinding
    private lateinit var factory: RoomViewModelFactory
    private val viewModel: RoomViewModel by viewModels { factory }

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)
        factory = RoomViewModelFactory((application as MyApplication).database.scheduleDao())

        initView()
    }

    private fun initView() {
        recyclerView = dataBinding.recycler
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = BusStopAdapter({
            println("itemClicl -> " + it)
        })
        recyclerView.adapter = adapter
        
//        GlobalScope.launch(Dispatchers.IO) {
//
//                adapter.submitList(viewModel.fullSchedule())
//        }
        lifecycle.coroutineScope.launch {
            viewModel.fullSchedule().collect {
                adapter.submitList(it)
            }
        }
    }


//    fun getData() {
//        GlobalScope.launch(Dispatchers.IO){
//            val list = viewModel.fullSchedule()
//            println("list =>" + list)
//
//        }
//    }

}