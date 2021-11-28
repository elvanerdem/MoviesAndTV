package com.elvanerdem.moviesandtv.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elvanerdem.moviesandtv.data.remote.api.MovieApi
import com.elvanerdem.moviesandtv.data.remote.api.TVSeriesApi
import com.elvanerdem.moviesandtv.model.tvseries.TVSeries
import com.elvanerdem.moviesandtv.utils.Constants
import javax.inject.Inject

/** 
 * Created by elvanerdem 25.10.2021 
 */
class TVSeriesPagingSource @Inject constructor(private val tvSeriesApi: TVSeriesApi): PagingSource<Int, TVSeries>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVSeries> {
        val pageNumber = params.key ?: 1
        return try {
            val response = tvSeriesApi.getPopularTVSeries(pageNumber)
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

    override fun getRefreshKey(state: PagingState<Int, TVSeries>): Int? = 1
}