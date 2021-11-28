package com.elvanerdem.moviesandtv.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elvanerdem.moviesandtv.data.remote.api.SearchApi
import com.elvanerdem.moviesandtv.model.search.SearchResult
import com.elvanerdem.moviesandtv.utils.Constants
import java.lang.Exception
import javax.inject.Inject

/** 
 * Created by elvanerdem 25.10.2021 
 */
class SearchPagingSource @Inject constructor(private val searchApi: SearchApi, private val query: String) : PagingSource<Int,SearchResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResult> {
        val pageNumber = params.key ?: 1
        return try {
            val response = searchApi.searchMoviesAndTVSeries(query, pageNumber)
            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (response.results.isEmpty()) null else pageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SearchResult>): Int? = 1

}