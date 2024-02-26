package com.example.searchapp_hw26.presentation.state

import com.example.searchapp_hw26.presentation.model.Category


data class SearchPageState(
    val isLoading: Boolean = false,
    val searchedCategory: List<Category>? = null,
    val errorMessage: String? = null
)