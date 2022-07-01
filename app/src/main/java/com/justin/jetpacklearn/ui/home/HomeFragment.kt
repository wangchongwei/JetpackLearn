package com.justin.jetpacklearn.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.justin.jetpacklearn.databinding.FragmentHomeBinding
import com.justin.jetpacklearn.ui.home.model.Item

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container, false)
        println("HomeFragment => onCreateView")

        initView()
        return binding.root
    }

    private fun initView() {
        var lists = listOf(
            Item("Justin.wang", "1"),
            Item("Bob.liu", "2"),
            Item("Angel.zhang", "3"),
            Item("Ruby.pang", "4"),
            Item("jackson Booty", "5"),
            Item("wart button", "6"),
        )
        var adapter = RecyclerViewAdapter(lists)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }


}