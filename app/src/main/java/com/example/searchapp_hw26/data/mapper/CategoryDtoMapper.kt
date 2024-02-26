package com.example.searchapp_hw26.data.mapper

import com.example.searchapp_hw26.data.model.CategoryDto
import com.example.searchapp_hw26.domain.model.CategoryDomain

fun CategoryDto.toDomain(): CategoryDomain = CategoryDomain(
    id = id,
    name = name,
    childrenDomain = childrenDto.map { it.toDomain() }
)