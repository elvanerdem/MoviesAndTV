package com.elvanerdem.moviesandtv.ui.tvseries.detail

import androidx.lifecycle.*
import com.elvanerdem.moviesandtv.data.Result
import com.elvanerdem.moviesandtv.data.local.WatchListDao
import com.elvanerdem.moviesandtv.data.repository.TVSeriesRepository
import com.elvanerdem.moviesandtv.model.tvseries.TVSeriesDetailResponse
import com.elvanerdem.moviesandtv.model.watchlist.WatchListEntity
import com.elvanerdem.moviesandtv.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/** 
 * Created by elvanerdem 7.05.2021 
 */
@HiltViewModel
class TVSeriesDetailViewModel @Inject constructor(private val tvSeriesRepository: TVSeriesRepository, private val watchListDao: WatchListDao): BaseViewModel() {

    private lateinit var _tvSeriesDetailResponse: LiveData<Result<TVSeriesDetailResponse>>
    val tvSeriesDetailResponse: LiveData<Result<TVSeriesDetailResponse>>
    get() = _tvSeriesDetailResponse

    private val _tvSeriesDetail = MutableLiveData<TVSeriesDetailResponse>()
    val tvSeriesDetail: LiveData<TVSeriesDetailResponse>
        get() = _tvSeriesDetail

    private val _isItemInWatchList = MutableLiveData<Boolean>()
    val isItemInWatchList: LiveData<Boolean>
        get() = _isItemInWatchList

    fun getTVSeriesDetail(tvId: Long){
        _tvSeriesDetailResponse = tvSeriesRepository.getTVSeriesDetail(tvId).onStart { Result.Loading(true) }.asLiveData()
    }

    fun setTVSeriesDetail(response: Result<TVSeriesDetailResponse>){
        when(response) {
            is Result.Success -> {
                response.data.let {
                    _tvSeriesDetail.value = it
                }
                setLoading(false)
            }
            else -> {
                super.setResponseData(response)
            }
        }
    }

    fun checkTVSeriesInWatchList(movieId: Long) {
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
        _tvSeriesDetail.value?.let {
            val item = WatchListEntity(it.id, it.orgName, it.posterPath, it.firstAirDate, it.voteAverage, it.formattedDuration, false)
            viewModelScope.launch {
                watchListDao.insertWatchList(item)
                _isItemInWatchList.value = true
            }
        }
    }

    private fun deleteFromWatchList() {
        _tvSeriesDetail.value?.let {
            val item = WatchListEntity(it.id, it.orgName, it.posterPath, it.firstAirDate, it.voteAverage, it.formattedDuration, false)
            viewModelScope.launch {
                watchListDao.deleteItemFromWatchList(item)
                _isItemInWatchList.value = false
            }
        }
    }
}