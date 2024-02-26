package com.example.searchapp_hw26.domain.usecase

import com.example.searchapp_hw26.presentation.model.Category
import javax.inject.Inject


class GetFilteredCategoriesUseCase @Inject constructor() {

    operator fun invoke(categories: List<Category>, name: String?): List<Category> {
        return if (name.isNullOrBlank())
            categories
        else
            categories.filter { it.name == name }
    }
}