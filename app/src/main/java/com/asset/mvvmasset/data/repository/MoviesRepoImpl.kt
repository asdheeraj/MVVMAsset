package com.asset.mvvmasset.data.repository

import com.asset.mvvmasset.data.model.MoviesResponse
import com.asset.mvvmasset.data.repository.dataSource.MoviesRemoteDataSource
import com.asset.mvvmasset.data.util.Resource
import com.asset.mvvmasset.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {

    override suspend fun getPopularMovies(
        language: String?,
        page: Int?,
        region: String?
    ): Resource<MoviesResponse> {
        return moviesRemoteDataSource.getPopularMovies(language, page, region)
    }
}