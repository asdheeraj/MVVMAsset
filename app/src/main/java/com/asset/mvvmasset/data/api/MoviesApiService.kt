package com.asset.mvvmasset.data.api

import com.asset.mvvmasset.data.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("popular")
    suspend fun getPopularMovies(
        @Query("language") language: String? = null,
        @Query("page") page: Int? = null,
        @Query("region") region: String? = null
    ) : Response<MoviesResponse>
}