package com.example.searchapp_hw26.presentation.extension

import com.example.searchapp_hw26.presentation.model.Category

fun Category.flattenTree(): MutableList<Category> {
    val flatList = mutableListOf<Category>()
    flatList.add(this)

    children.forEach { child ->
        flatList.addAll(child.flattenTree())
    }

    return flatList
}