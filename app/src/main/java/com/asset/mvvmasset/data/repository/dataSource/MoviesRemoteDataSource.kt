package com.asset.mvvmasset.data.repository.dataSource

import com.asset.mvvmasset.data.model.MoviesResponse
import retrofit2.Response

interface MoviesRemoteDataSource {
    suspend fun getPopularMovies(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ) : Response<MoviesResponse>
}