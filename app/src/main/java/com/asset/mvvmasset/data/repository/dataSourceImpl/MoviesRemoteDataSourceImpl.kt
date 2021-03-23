package com.asset.mvvmasset.data.repository.dataSourceImpl

import com.asset.mvvmasset.data.api.MoviesApiService
import com.asset.mvvmasset.data.model.MoviesResponse
import com.asset.mvvmasset.data.repository.dataSource.MoviesRemoteDataSource
import com.asset.mvvmasset.data.util.Resource
import com.asset.mvvmasset.utils.safeApiCall
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val moviesApiService: MoviesApiService
) : MoviesRemoteDataSource {
    override suspend fun getPopularMovies(
        language: String?,
        page: Int?,
        region: String?
    ): Resource<MoviesResponse> {
        return safeApiCall {
            moviesApiService.getPopularMovies(language, page, region)
        }
    }
}