package com.elvanerdem.moviesandtv.data.remote.api

import com.elvanerdem.moviesandtv.model.base.BaseResponse
import com.elvanerdem.moviesandtv.model.tvseries.TVSeries
import com.elvanerdem.moviesandtv.model.tvseries.TVSeriesDetailResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by elvanerdem on 7.02.2021.
 */
interface TVSeriesApi {

    @GET("tv/popular")
    suspend fun getPopularTVSeries(@Query("page") page: Int): BaseResponse<TVSeries>

    @GET("tv/{tvId}")
    suspend fun getTVSeriesDetail(@Path("tvId") movieId: Long): TVSeriesDetailResponse

}