package com.example.searchapp_hw26.presentation.model


data class Category(
    val id: String,
    val name: String,
    val children: List<Category>,
    val parentCount: Int
)
