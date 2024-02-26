package com.example.searchapp_hw26.domain.model


data class CategoryDomain(
    val id: String,
    val name: String,
    val childrenDomain: List<CategoryDomain>
)