package com.asset.mvvmasset.domain.usecase

import com.asset.mvvmasset.domain.repository.MoviesRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend fun execute(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ) = moviesRepository.getPopularMovies(language, page, region)
}