package com.asset.mvvmasset.data.repository.dataSource

import com.asset.mvvmasset.data.model.MoviesResponse
import com.asset.mvvmasset.data.util.Resource

interface MoviesRemoteDataSource {
    suspend fun getPopularMovies(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ) : Resource<MoviesResponse>
}