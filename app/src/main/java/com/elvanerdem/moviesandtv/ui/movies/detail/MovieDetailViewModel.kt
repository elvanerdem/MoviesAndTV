package com.elvanerdem.moviesandtv.ui.movies.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.elvanerdem.moviesandtv.data.Result
import com.elvanerdem.moviesandtv.data.local.WatchListDao
import com.elvanerdem.moviesandtv.data.repository.MovieRepository
import com.elvanerdem.moviesandtv.model.movie.MovieDetailResponse
import com.elvanerdem.moviesandtv.model.watchlist.WatchListEntity
import com.elvanerdem.moviesandtv.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by elvanerdem on 7.02.2021.
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val movieRepository: MovieRepository, private val watchListDao: WatchListDao): BaseViewModel() {

    private lateinit var _movieDetailResponse: LiveData<Result<MovieDetailResponse>>
    val movieDetailResponse: LiveData<Result<MovieDetailResponse>>
        get() = _movieDetailResponse

    private var _movieDetail = MutableLiveData<MovieDetailResponse>()
    val movieDetail: LiveData<MovieDetailResponse>
        get() = _movieDetail

    private val _isItemInWatchList = MutableLiveData<Boolean>()
    val isItemInWatchList: LiveData<Boolean>
    get() = _isItemInWatchList

    fun getMovieDetail(movieId: Long) {
        _movieDetailResponse = movieRepository.getMovieDetail(movieId).onStart { emit(Result.Loading(true)) }.asLiveData()
    }

    fun setMovieDetail(response: Result<MovieDetailResponse>) {
        when (response) {
            is Result.Success -> {
                response.data.let {
                    _movieDetail.value = it
                }
                setLoading(false)
            }
            else -> {
                super.setResponseData(response)
            }
        }
    }

    fun checkMovieInWatchList(movieId: Long) {
        viewModelScope.launch {
            val watchList = watchListDao.getWatchListItemsById(movieId)
            if (watchList?.name != null) {
                _isItemInWatchList.value = true
            }
        }
    }

    fun onAddToWatchListClicked() {
        if (_isItemInWatchList.value == true) {
            deleteFromWatchList()
        } else {
            insertWatchList()
        }
    }

    private fun insertWatchList() {
        _movieDetail.value?.let {
            val item = WatchListEntity(it.id, it.orgTitle, it.posterPath, it.releaseDate, it.voteAverage, it.formattedDuration, true)
            viewModelScope.launch {
                watchListDao.insertWatchList(item)
                _isItemInWatchList.value = true
            }
        }
    }

    private fun deleteFromWatchList() {
        _movieDetail.value?.let {
            val item = WatchListEntity(it.id, it.orgTitle, it.posterPath, it.releaseDate, it.voteAverage, it.formattedDuration, false)
            viewModelScope.launch {
                watchListDao.deleteItemFromWatchList(item)
                _isItemInWatchList.value = false
            }
        }
    }
}