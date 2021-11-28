package com.elvanerdem.moviesandtv.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.elvanerdem.moviesandtv.data.remote.api.SearchApi
import com.elvanerdem.moviesandtv.data.remote.paging.SearchPagingSource
import com.elvanerdem.moviesandtv.model.search.SearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

/** 
 * Created by elvanerdem 6.10.2021 
 */
class SearchRepository @Inject constructor(private val searchApi: SearchApi){

    fun search(query: String): Flow<PagingData<SearchResult>> {
        return Pager(
            pagingSourceFactory = { SearchPagingSource(searchApi, query) },
            config = PagingConfig(20)
        ).flow
    }

}