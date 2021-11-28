package com.elvanerdem.moviesandtv.model.base

import com.squareup.moshi.Json

/**
 * Created by elvanerdem on 7.02.2021.
 */
data class BaseResponse<T>(
    val page: Int,
    val results: List<T>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)
