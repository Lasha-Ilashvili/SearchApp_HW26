package com.example.searchapp_hw26.presentation.screen.search_page.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchapp_hw26.databinding.DotItemBinding

class DotsRecyclerViewAdapter : RecyclerView.Adapter<DotsRecyclerViewAdapter.DotItemViewHolder>() {

    private var size: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotItemViewHolder {
        return DotItemViewHolder(
            DotItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setSize(newSize: Int) {
        size = newSize
    }

    override fun getItemCount() = size

    override fun onBindViewHolder(holder: DotItemViewHolder, position: Int) {
        holder.bind()
    }


    inner class DotItemViewHolder(private val binding: DotItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {

        }
    }
}