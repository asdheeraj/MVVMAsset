package com.asset.mvvmasset.domain.usecase

import com.asset.mvvmasset.data.model.MoviesResponse
import com.asset.mvvmasset.data.util.Resource
import com.asset.mvvmasset.domain.repository.MoviesRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend fun execute(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): Resource<MoviesResponse> {
        return try {
            moviesRepository.getPopularMovies(language, page, region)
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "Invalid Exception thrown")
        }
    }
}