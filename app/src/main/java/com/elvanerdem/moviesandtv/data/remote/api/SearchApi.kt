package com.elvanerdem.moviesandtv.data.remote.api

import com.elvanerdem.moviesandtv.model.base.BaseResponse
import com.elvanerdem.moviesandtv.model.search.SearchResult
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/** 
 * Created by elvanerdem on 6.10.2021
 */
interface SearchApi {

    @GET("search/multi")
    suspend fun searchMoviesAndTVSeries(@Query("query") query: String, @Query("page") page: Int): BaseResponse<SearchResult>

}