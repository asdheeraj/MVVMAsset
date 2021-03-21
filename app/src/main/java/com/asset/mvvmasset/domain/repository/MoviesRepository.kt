package com.asset.mvvmasset.domain.repository

import com.asset.mvvmasset.data.model.MoviesResponse
import com.asset.mvvmasset.data.util.Resource

interface MoviesRepository {
    suspend fun getPopularMovies(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): Resource<MoviesResponse>
}