package com.justin.jetpacklearn.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.justin.jetpacklearn.databinding.FragmentMineBinding
import com.justin.jetpacklearn.ui.home.RecyclerViewAdapter
import com.justin.jetpacklearn.ui.home.model.Item

class MineFragment : Fragment() {

    private lateinit var binding: FragmentMineBinding

    private var hasInflated: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMineBinding.inflate(inflater, container, false)
        println("MineFragment => onCreateView")
        initView()
        return binding.root
    }

    private fun initView() {
        binding.startLoading.setOnClickListener {
            showLoading()
        }

        binding.hideLoading.setOnClickListener {
            hideLoading()
        }

        var lists = listOf(
            Item("Justin.wang", "1"),
            Item("Bob.liu", "2"),
            Item("Angel.zhang", "3"),
            Item("Ruby.pang", "4"),
            Item("jackson Booty", "5"),
            Item("wart button", "6"),
            Item("Justin.wang", "7"),
            Item("Bob.liu", "8"),
            Item("Angel.zhang", "9"),
            Item("Ruby.pang", "10"),
            Item("jackson Booty", "11"),
            Item("wart button", "12"),

            Item("Justin.wang", "13"),
            Item("Bob.liu", "14"),
            Item("Angel.zhang", "15"),
            Item("Ruby.pang", "16"),
            Item("jackson Booty", "17"),
            Item("wart button", "18"),
            Item("Justin.wang", "19"),
            Item("Bob.liu", "20"),
            Item("Angel.zhang", "21"),
            Item("Ruby.pang", "22"),
            Item("jackson Booty", "23"),
            Item("wart button", "24"),
        )
        var adapter = RecyclerViewAdapter(lists)
        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.adapter = adapter
    }

    private fun showLoading() {
        if(hasInflated) {
            binding.loadingView.visibility = View.VISIBLE
        } else {
            inflateLoading()
        }
    }

    private fun hideLoading() {
        binding.loadingView.visibility = View.INVISIBLE
    }

    private fun inflateLoading() {
        binding.loadingView.inflate()
        hasInflated = true
    }

}