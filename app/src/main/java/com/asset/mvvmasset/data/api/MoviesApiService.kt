package com.asset.mvvmasset.data.api

import com.asset.mvvmasset.BuildConfig
import com.asset.mvvmasset.data.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1,
        @Query("region") region: String? = null,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<MoviesResponse>
}