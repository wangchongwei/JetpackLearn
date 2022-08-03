package com.justin.jetpacklearn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.justin.jetpacklearn.databinding.ActivityUiBinding
import com.justin.jetpacklearn.ui.home.HomeFragment
import com.justin.jetpacklearn.ui.home.ViewPagerAdapter
import com.justin.jetpacklearn.ui.mine.MineFragment

class UIActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initView()
    }

    private fun initView() {
        var fragments = listOf(HomeFragment(), MineFragment(), HomeFragment())
        var adapter = ViewPagerAdapter(this, fragments)
        binding.viewPager.adapter = adapter
        var tabList = arrayOf("Home", "Mine","Setting")

        TabLayoutMediator(binding.tabLayout, binding.viewPager){
            tab, position -> tab.text = tabList[position]
        }.attach()

    }

    private fun initData() {
    }
}