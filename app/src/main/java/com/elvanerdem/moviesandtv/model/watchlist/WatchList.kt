package com.elvanerdem.moviesandtv.model.watchlist

/**
 * Created by elvanerdem on 19.09.2021.
 */
data class WatchList(
    var id: Long = 0,
    var name: String,
    var posterPath: String,
    var releaseDate: String,
    var voteAverage: Double,
    var duration: String,
    var isMovie: Boolean
)