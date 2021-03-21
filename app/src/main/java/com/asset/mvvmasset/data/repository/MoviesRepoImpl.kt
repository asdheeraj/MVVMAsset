package com.asset.mvvmasset.data.repository

import com.asset.mvvmasset.data.model.MoviesResponse
import com.asset.mvvmasset.data.repository.dataSource.MoviesRemoteDataSource
import com.asset.mvvmasset.data.util.Resource
import com.asset.mvvmasset.domain.repository.MoviesRepository
import retrofit2.Response

class MoviesRepoImpl(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {

    override suspend fun getPopularMovies(
        language: String?,
        page: Int?,
        region: String?
    ): Resource<MoviesResponse> {
        return responseToResource(moviesRemoteDataSource.getPopularMovies(language, page, region))
    }

    private fun responseToResource(
        response: Response<MoviesResponse>
    ): Resource<MoviesResponse> {
        if (response.isSuccessful) {
            return response.body()?.let { movies ->
                Resource.Success(movies)
            } ?: Resource.Error(response.message())
        }
        return Resource.Error(response.message())
    }
}