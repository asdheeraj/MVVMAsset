package com.asset.mvvmasset.data.api


import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesApiServiceTest {

    private lateinit var moviesApiService: MoviesApiService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        moviesApiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApiService::class.java)
    }

    private fun enqueueMockResponse(fileName: String) {
        javaClass.classLoader?.getResourceAsStream(fileName)?.let { inputStream ->
            val mockResponse = MockResponse().apply {
                setBody(inputStream.source().buffer().readString(Charsets.UTF_8))
            }
            mockWebServer.enqueue(mockResponse)
        }
    }

    @Test
    fun getPopularMovies_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("moviesresponse.json")
            val response = moviesApiService.getPopularMovies().body()
            val request = mockWebServer.takeRequest()
            assertThat(response).isNotNull()
            assertThat(request.path).isEqualTo("/movie/popular?language=en-US&page=1&api_key=f559cbf805979a54e983cb35c5cfbc26")
            assertThat(response?.results?.size == 20)
        }
    }

    @Test
    fun getPopularMovies_receivedResponse_pageCount() {
        runBlocking {
            enqueueMockResponse("moviesresponse.json")
            val response = moviesApiService.getPopularMovies().body()
            assertThat(response?.results?.size == 20)
        }
    }

    @Test
    fun getPopularMovies_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("moviesresponse.json")
            val response = moviesApiService.getPopularMovies().body()
            response?.results?.first()?.let { result ->
                assertThat(result.backdrop_path).isEqualTo("/hJuDvwzS0SPlsE6MNFOpznQltDZ.jpg")
                assertThat(result.original_title).isEqualTo("Raya and the Last Dragon")
            }

        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}