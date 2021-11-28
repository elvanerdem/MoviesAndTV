package com.elvanerdem.moviesandtv.ui.movies

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.elvanerdem.moviesandtv.data.repository.MovieRepository
import com.elvanerdem.moviesandtv.model.movie.Movie
import com.elvanerdem.moviesandtv.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by elvanerdem on 7.02.2021.
 */
@HiltViewModel
class MoviesViewModel @Inject constructor(private val movieRepository: MovieRepository): BaseViewModel() {

    private lateinit var _movieListFlow: Flow<PagingData<Movie>>
    val movieListFlow: Flow<PagingData<Movie>>
    get() = _movieListFlow

    init {
        getPopularMovieList()
    }

    private fun getPopularMovieList() {
        viewModelScope.launch {
            _movieListFlow = movieRepository.getPopularMovies().cachedIn(viewModelScope)
        }
    }

}