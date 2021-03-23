package com.asset.mvvmasset.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asset.mvvmasset.data.model.MoviesResponse
import com.asset.mvvmasset.data.util.Resource
import com.asset.mvvmasset.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    init {
        getPopularMovies()
    }

    val popularMovies: MutableLiveData<Resource<MoviesResponse>> = MutableLiveData()

    private fun getPopularMovies(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ) = viewModelScope.launch(dispatcher) {
        with(popularMovies) {
            postValue(Resource.Loading())
            postValue(getPopularMoviesUseCase.execute(language, page, region))
        }
    }
}