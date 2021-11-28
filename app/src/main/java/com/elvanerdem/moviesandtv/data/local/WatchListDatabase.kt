package com.elvanerdem.moviesandtv.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elvanerdem.moviesandtv.model.watchlist.WatchListEntity
import com.elvanerdem.moviesandtv.utils.Constants

/**
 * Created by elvanerdem on 19.09.2021.
 */
@Database(entities = [WatchListEntity::class], version = 1)
abstract class WatchListDatabase: RoomDatabase() {

    abstract fun watchListDao(): WatchListDao

    companion object {

        @Volatile private var instance : WatchListDatabase? = null

        fun getInstance(context: Context): WatchListDatabase {
            return  instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance =it
                }
            }
        }

        private fun buildDatabase(context: Context): WatchListDatabase = Room.databaseBuilder(context.applicationContext, WatchListDatabase::class.java, Constants.DATABASE_NAME).build()

    }
}