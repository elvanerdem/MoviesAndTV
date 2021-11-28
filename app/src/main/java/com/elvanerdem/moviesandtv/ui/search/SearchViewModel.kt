package com.elvanerdem.moviesandtv.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.elvanerdem.moviesandtv.data.repository.SearchRepository
import com.elvanerdem.moviesandtv.model.search.SearchResult
import com.elvanerdem.moviesandtv.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** 
 * Created by elvanerdem 6.10.2021 
 */
@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : BaseViewModel() {

    fun search(query: String): Flow<PagingData<SearchResult>> {
        return searchRepository.search(query)
    }

}