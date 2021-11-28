package com.elvanerdem.moviesandtv.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.elvanerdem.moviesandtv.model.watchlist.WatchListEntity

/**
 * Created by elvanerdem on 19.09.2021.
 */
@Dao
interface WatchListDao {

    @Query("SELECT * FROM watchlistentity")
    suspend fun getWatchListItems(): List<WatchListEntity>

    @Query("SELECT * FROM watchlistentity WHERE id=:id")
    suspend fun getWatchListItemsById(id: Long): WatchListEntity?

    @Insert
    suspend fun insertWatchList(item: WatchListEntity): Long

    @Delete
    suspend fun deleteItemFromWatchList(item: WatchListEntity)
}