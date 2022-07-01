package com.justin.jetpacklearn.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.justin.jetpacklearn.databinding.FragmentMineBinding

class MineFragment : Fragment() {

    private lateinit var binding: FragmentMineBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMineBinding.inflate(inflater, container, false)
        println("MineFragment => onCreateView")
        return binding.root
    }

}