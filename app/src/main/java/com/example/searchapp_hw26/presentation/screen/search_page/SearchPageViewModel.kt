package com.example.searchapp_hw26.presentation.screen.search_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchapp_hw26.data.common.Resource
import com.example.searchapp_hw26.domain.usecase.GetCategoriesUseCase
import com.example.searchapp_hw26.domain.usecase.GetFilteredCategoriesUseCase
import com.example.searchapp_hw26.presentation.event.SearchPageEvent
import com.example.searchapp_hw26.presentation.mapper.toPresentation
import com.example.searchapp_hw26.presentation.model.Category
import com.example.searchapp_hw26.presentation.state.SearchPageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class SearchPageViewModel @Inject constructor(
    private val categories: GetCategoriesUseCase,
    private val filteredCategories: GetFilteredCategoriesUseCase
) : ViewModel() {

    init {
        searchCategory()
    }

    private val _searchPage = MutableStateFlow(SearchPageState())
    val searchPage get() = _searchPage.asStateFlow()

    fun onEvent(event: SearchPageEvent) {
        when (event) {
            is SearchPageEvent.SearchCategory -> searchCategory(event.name)
            is SearchPageEvent.ResetErrorMessage -> updateErrorMessage()
        }
    }

    private fun searchCategory(name: String? = null) {
        viewModelScope.launch {
            categories(name).collect {
                when (it) {
                    is Resource.Success -> {
                        _searchPage.update { currentState ->
                            withContext(Dispatchers.Default) {
                                val result = it.data.map { domain ->
                                    domain.toPresentation().flattenTree()
                                }.flatten()

                                currentState.copy(
                                    searchedCategory = filteredCategories(result, name)
                                )
                            }
                        }
                    }

                    is Resource.Error -> updateErrorMessage(message = it.errorMessage)

                    is Resource.Loading -> _searchPage.update { currentState ->
                        currentState.copy(isLoading = it.loading)
                    }
                }
            }
        }
    }

    private fun Category.flattenTree(): MutableList<Category> {
        val flatList = mutableListOf<Category>()
        flatList.add(this)

        children.forEach { child ->
            flatList.addAll(child.flattenTree())
        }

        return flatList
    }

    private fun updateErrorMessage(message: String? = null) {
        _searchPage.update { currentState ->
            currentState.copy(errorMessage = message)
        }
    }
}