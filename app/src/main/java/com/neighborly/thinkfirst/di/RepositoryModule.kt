package com.neighborly.thinkfirst.di

import com.neighborly.thinkfirst.data.repository.AppRepositoryImpl
import com.neighborly.thinkfirst.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAppRepository(
        repository: AppRepositoryImpl
    ): AppRepository
}