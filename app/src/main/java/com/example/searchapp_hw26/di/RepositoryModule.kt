package com.example.searchapp_hw26.di

import com.example.searchapp_hw26.data.common.HandleResponse
import com.example.searchapp_hw26.data.repository.CategoriesRepositoryImpl
import com.example.searchapp_hw26.data.service.CategoriesService
import com.example.searchapp_hw26.domain.repository.CategoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSignUpRepository(
        categoriesService: CategoriesService,
        handleResponse: HandleResponse
    ): CategoriesRepository {
        return CategoriesRepositoryImpl(categoriesService, handleResponse)
    }
}