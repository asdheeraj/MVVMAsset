package com.asset.mvvmasset.di

import com.asset.mvvmasset.data.api.MoviesApiService
import com.asset.mvvmasset.data.repository.MoviesRepoImpl
import com.asset.mvvmasset.data.repository.dataSource.MoviesRemoteDataSource
import com.asset.mvvmasset.data.repository.dataSourceImpl.MoviesRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Singleton
    @Provides
    fun provideMoviesRemoteDataSource(moviesApiService: MoviesApiService) : MoviesRemoteDataSource
            = MoviesRemoteDataSourceImpl(moviesApiService)
}