package com.elvanerdem.moviesandtv.model.movie

import com.squareup.moshi.Json

/**
 * Created by elvanerdem on 7.02.2021.
 */
data class Movie(
        val id: Long,
        @Json(name = "original_title")
        val orgTitle: String,
        val overview: String,
        val popularity: Double,
        @Json(name = "poster_path")
        val posterPath: String,
        @Json(name = "release_date")
        val releaseDate: String,
        val video: Boolean,
        @Json(name = "vote_average")
        val voteAverage: Double,
        @Json(name = "vote_count")
        val voteCount: Long
)
