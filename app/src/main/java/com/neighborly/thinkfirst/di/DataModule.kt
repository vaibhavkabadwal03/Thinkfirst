package com.neighborly.thinkfirst.di

import com.neighborly.thinkfirst.data.datasource.PackageManagerDataSource
import com.neighborly.thinkfirst.data.datasource.PackageManagerDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindPackageManagerDataSource(
        implementation: PackageManagerDataSourceImpl
    ): PackageManagerDataSource
}