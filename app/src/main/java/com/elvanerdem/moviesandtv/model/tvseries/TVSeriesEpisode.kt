package com.elvanerdem.moviesandtv.model.tvseries

import com.squareup.moshi.Json

/** 
 * Created by elvanerdem 7.05.2021 
 */
data class TVSeriesEpisode(
    @Json(name = "air_date")
    val airDate: String,
    @Json(name = "episode_number")
    val episodeNumber: Int,
    @Json(name = "season_number")
    val seasonNumber: Int,
    val name: String,
    val overview: String)
{
    val seasonAndEpisode: String
    get() = "Season $seasonNumber Episode $episodeNumber"
}