package com.example.searchapp_hw26.presentation.event


sealed class SearchPageEvent {
    data class SearchCategory(val name: String) : SearchPageEvent()
    data object ResetErrorMessage : SearchPageEvent()
}