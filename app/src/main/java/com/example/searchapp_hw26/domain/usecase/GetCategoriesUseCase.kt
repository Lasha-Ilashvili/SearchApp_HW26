package com.example.searchapp_hw26.domain.usecase

import com.example.searchapp_hw26.domain.repository.CategoriesRepository
import javax.inject.Inject


class GetCategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) {
    suspend operator fun invoke(name: String?) =
        categoriesRepository.getSearchedCategory(name = name)
}