package com.elvanerdem.moviesandtv.ui.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.elvanerdem.moviesandtv.data.repository.TVSeriesRepository
import com.elvanerdem.moviesandtv.model.tvseries.TVSeries
import com.elvanerdem.moviesandtv.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by elvanerdem on 7.02.2021.
 */
@HiltViewModel
class TvSeriesViewModel @Inject constructor(private val tvSeriesRepository: TVSeriesRepository): BaseViewModel() {

    private lateinit var _tvSeriesListFlow: Flow<PagingData<TVSeries>>
    val tvSeriesListFlow: Flow<PagingData<TVSeries>>
    get() = _tvSeriesListFlow

    init {
        getTVSeriesList()
    }

    private fun getTVSeriesList() {
        viewModelScope.launch {
            _tvSeriesListFlow = tvSeriesRepository.getPopularTVSeries()
        }
    }
}