package com.example.searchapp_hw26.domain.repository

import com.example.searchapp_hw26.data.common.Resource
import com.example.searchapp_hw26.domain.model.CategoryDomain
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {

    suspend fun getSearchedCategory(name: String?): Flow<Resource<List<CategoryDomain>>>
}