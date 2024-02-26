package com.example.searchapp_hw26.presentation.mapper

import com.example.searchapp_hw26.domain.model.CategoryDomain
import com.example.searchapp_hw26.presentation.model.Category


fun CategoryDomain.toPresentation(parentCount: Int = 0): Category = Category(
    id = id,
    name = name,
    children = childrenDomain.map { it.toPresentation(parentCount + 1) },
    parentCount = parentCount
)