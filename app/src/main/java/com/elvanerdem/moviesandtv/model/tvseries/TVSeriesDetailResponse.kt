package com.elvanerdem.moviesandtv.model.tvseries

import com.elvanerdem.moviesandtv.model.Genre
import com.squareup.moshi.Json

/**
 * Created by elvanerdem on 7.02.2021.
 */
data class TVSeriesDetailResponse(

    val id: Long = 0,
    @Json(name = "original_name")
    val orgName: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    @Json(name = "episode_run_time")
    val runtime: List<Int>,
    @Json(name = "poster_path")
    val posterPath: String = "",
    @Json(name = "first_air_date")
    val firstAirDate: String,
    val genres: List<Genre>,
    @Json(name = "vote_average")
    val voteAverage: Double = 0.0,
    @Json(name = "vote_count")
    val voteCount: Long = 0,
    @Json(name = "last_episode_to_air")
    val lastEpisode: TVSeriesEpisode

){
    val formattedDuration: String
        get() = runtime.let {
            "${runtime[0]} Dk"
        }
    val genresDescription: String
        get() = genres.joinToString(separator = " | ") { it.name }
}