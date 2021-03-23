package com.asset.mvvmasset.di

import com.asset.mvvmasset.data.repository.MoviesRepoImpl
import com.asset.mvvmasset.data.repository.dataSource.MoviesRemoteDataSource
import com.asset.mvvmasset.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepo(moviesRemoteDataSource: MoviesRemoteDataSource) : MoviesRepository
            = MoviesRepoImpl(moviesRemoteDataSource)
}