package com.elvanerdem.moviesandtv.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.elvanerdem.moviesandtv.data.Result
import com.elvanerdem.moviesandtv.data.remote.api.TVSeriesApi
import com.elvanerdem.moviesandtv.data.remote.paging.TVSeriesPagingSource
import com.elvanerdem.moviesandtv.model.tvseries.TVSeries
import com.elvanerdem.moviesandtv.model.tvseries.TVSeriesDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by elvanerdem on 7.02.2021.
 */
class TVSeriesRepository @Inject constructor(private val tvSeriesApi: TVSeriesApi) {

    fun getPopularTVSeries(): Flow<PagingData<TVSeries>> {
       return Pager(
           config = PagingConfig(20),
           pagingSourceFactory = { TVSeriesPagingSource(tvSeriesApi)}
       ).flow
    }

    fun getTVSeriesDetail(tvId: Long): Flow<Result<TVSeriesDetailResponse>> = flow{
        try {
            val response = tvSeriesApi.getTVSeriesDetail(tvId)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Something went wrong"))
        }
    }

}