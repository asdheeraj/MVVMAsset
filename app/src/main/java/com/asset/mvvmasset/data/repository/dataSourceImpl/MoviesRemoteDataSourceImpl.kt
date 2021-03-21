package com.asset.mvvmasset.data.repository.dataSourceImpl

import com.asset.mvvmasset.data.api.MoviesApiService
import com.asset.mvvmasset.data.model.MoviesResponse
import com.asset.mvvmasset.data.repository.dataSource.MoviesRemoteDataSource
import retrofit2.Response

class MoviesRemoteDataSourceImpl(
    private val moviesApiService: MoviesApiService
) : MoviesRemoteDataSource {
    override suspend fun getPopularMovies(
        language: String?,
        page: Int?,
        region: String?
    ): Response<MoviesResponse> {
        return moviesApiService.getPopularMovies(language, page, region)
    }
}