package com.example.searchapp_hw26.data.model

import com.squareup.moshi.Json


data class CategoryDto(
    val id: String,
    val name: String,
    @Json(name = "children")
    val childrenDto: List<CategoryDto>
)