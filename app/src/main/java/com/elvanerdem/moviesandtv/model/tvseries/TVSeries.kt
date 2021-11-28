package com.elvanerdem.moviesandtv.model.tvseries

import com.squareup.moshi.Json

/**
 * Created by elvanerdem on 7.02.2021.
 */
data class TVSeries(
     val id: Long = 0,
     @Json(name = "original_name")
     val orgName: String = "",
     val overview: String = "",
     val popularity: Double = 0.0,
     @Json(name = "poster_path")
     val posterPath: String? = "",
     @Json(name = "first_air_date")
     val firstAirDate: String = "",
     @Json(name = "vote_average")
     val voteAverage: Double = 0.0,
     @Json(name = "vote_count")
     val voteCount: Long = 0
)