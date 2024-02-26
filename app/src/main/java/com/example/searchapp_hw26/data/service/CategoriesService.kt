package com.example.searchapp_hw26.data.service

import com.example.searchapp_hw26.data.model.CategoryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesService {
    @GET("6f722f19-3297-4edd-a249-fe765e8104db")
    suspend fun getSearchedCategory(@Query("search") name: String?): Response<List<CategoryDto>>
}