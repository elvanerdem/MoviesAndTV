package com.elvanerdem.moviesandtv.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvanerdem.moviesandtv.data.local.WatchListDao
import com.elvanerdem.moviesandtv.model.watchlist.WatchList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by elvanerdem on 7.02.2021.
 */
@HiltViewModel
class WatchListViewModel @Inject constructor(private val watchListDao: WatchListDao): ViewModel() {

    private var _watchList = MutableLiveData<List<WatchList>>()
    val watchList: LiveData<List<WatchList>>
    get() = _watchList

    init {
        getWatchList()
    }

    fun getWatchList() {
        viewModelScope.launch {
            val entityList = watchListDao.getWatchListItems()
            var list = mutableListOf<WatchList>()
            entityList.let {
                for (entity in entityList) {
                    val watchListItem = WatchList(entity.id, entity.name, entity.posterPath, entity.releaseDate, entity.voteAverage, entity.duration, entity.isMovie)
                    list.add(watchListItem)
                }
            }
            _watchList.value = list
        }
    }

}