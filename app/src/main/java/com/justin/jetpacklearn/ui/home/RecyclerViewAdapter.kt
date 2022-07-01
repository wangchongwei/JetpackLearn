package com.justin.jetpacklearn.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.justin.jetpacklearn.R
import com.justin.jetpacklearn.databinding.ItemRecyclerviewHomeFragmentBinding
import com.justin.jetpacklearn.ui.home.model.Item

class RecyclerViewAdapter(var lists: List<Item>): RecyclerView.Adapter<RecyclerViewAdapter.MineViewHolder>() {







    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MineViewHolder {
        var binding: ItemRecyclerviewHomeFragmentBinding = ItemRecyclerviewHomeFragmentBinding.inflate(LayoutInflater.from(parent.context))
        return MineViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MineViewHolder, position: Int) {
        holder.textView.text = lists[position].name
    }

    override fun getItemCount(): Int {
        return lists.size
    }


    class MineViewHolder(var itenView: View): RecyclerView.ViewHolder(itenView) {
        var textView: TextView

        init {
            textView = itenView.findViewById(R.id.textView)
        }
    }
}