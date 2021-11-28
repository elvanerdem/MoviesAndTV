package com.elvanerdem.moviesandtv.model.search

import com.squareup.moshi.Json

/** 
 * Created by elvanerdem 6.10.2021 
 */
data class SearchResult(
        val id: Long,
        @Json(name = "original_title")
        val orgTitle: String = "",
        @Json(name = "original_name")
        val orgName: String = "",
        @Json(name = "media_type")
        val mediaType: String = "",
        val overview: String = "",
        val popularity: Double = 0.0,
        @Json(name = "poster_path")
        val posterPath: String? = "",
        @Json(name = "release_date")
        val releaseDate: String = "",
        @Json(name = "first_air_date")
        val firstAirDate: String = "",
        val video: Boolean = false,
        @Json(name = "vote_average")
        val voteAverage: Double = 0.0,
        @Json(name = "vote_count")
        val voteCount: Long = 0
) {
    val name: String
        get() = when (mediaType) {
            "tv" -> orgName
            "movie" -> orgTitle
            else -> ""
        }

}
