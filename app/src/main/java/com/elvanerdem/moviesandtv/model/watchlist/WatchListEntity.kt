package com.elvanerdem.moviesandtv.model.watchlist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by elvanerdem on 19.09.2021.
 */
@Entity
data class WatchListEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Long = 0,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "posterPath")
    val posterPath: String = "",
    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,
    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double,
    @ColumnInfo(name = "duration")
    var duration: String,
    @ColumnInfo(name = "isMovie")
    var isMovie: Boolean
)