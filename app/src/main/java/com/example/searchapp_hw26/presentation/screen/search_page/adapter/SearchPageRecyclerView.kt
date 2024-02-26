package com.example.searchapp_hw26.presentation.screen.search_page.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchapp_hw26.databinding.SearchItemBinding
import com.example.searchapp_hw26.presentation.model.Category

class SearchPageRecyclerView :
    ListAdapter<Category, SearchPageRecyclerView.SearchPageViewHolder>(SearchPageDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPageViewHolder {
        return SearchPageViewHolder(
            SearchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchPageViewHolder, position: Int) {
        holder.bind()
    }

    inner class SearchPageViewHolder(private val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val category = currentList[adapterPosition]

            with(binding) {
                tvName.text = category.name
            }
        }
    }

    object SearchPageDiffUtil : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
}