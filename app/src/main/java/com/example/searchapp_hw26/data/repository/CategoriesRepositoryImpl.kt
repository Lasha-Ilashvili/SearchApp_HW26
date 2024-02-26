package com.example.searchapp_hw26.data.repository

import android.util.Log
import com.example.searchapp_hw26.data.common.HandleResponse
import com.example.searchapp_hw26.data.common.Resource
import com.example.searchapp_hw26.data.mapper.base.asResource
import com.example.searchapp_hw26.data.mapper.toDomain
import com.example.searchapp_hw26.data.service.CategoriesService
import com.example.searchapp_hw26.domain.model.CategoryDomain
import com.example.searchapp_hw26.domain.repository.CategoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val categoriesService: CategoriesService,
    private val handleResponse: HandleResponse
) : CategoriesRepository {

    override suspend fun getSearchedCategory(name: String?): Flow<Resource<List<CategoryDomain>>> {
        return handleResponse.safeApiCall {
            categoriesService.getSearchedCategory(name = name)
        }.asResource {
            withContext(Dispatchers.Default) {
                it.map { dto ->
                    dto.toDomain()
                }
            }
        }
    }
}