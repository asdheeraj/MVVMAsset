package com.asset.mvvmasset.utils

import com.asset.mvvmasset.data.util.Resource
import retrofit2.Response

suspend fun <T : Any> safeApiCall(
    call: suspend () -> Response<T>
): Resource<T> = try {
    val response = call.invoke()
    if (response.isSuccessful) {
        responseToResource(response)
    } else {
        Resource.Error(message = response.message())
    }
} catch (e: Exception) {
    Resource.Error(message = e.message ?: "Unknown Exception")
}

private fun <T : Any> responseToResource(
    response: Response<T>
): Resource<T> {
    if (response.isSuccessful) {
        return response.body()?.let { movies ->
            Resource.Success(movies)
        } ?: Resource.Error(response.message())
    }
    return Resource.Error(response.message())
}