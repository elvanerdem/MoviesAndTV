package com.elvanerdem.moviesandtv.di

import android.content.Context
import com.elvanerdem.moviesandtv.data.local.WatchListDao
import com.elvanerdem.moviesandtv.data.local.WatchListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by elvanerdem on 19.09.2021.
 */
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideWatchListDatabase(@ApplicationContext context: Context): WatchListDatabase {
        return WatchListDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideWatchListDao(watchListDatabase: WatchListDatabase): WatchListDao {
        return watchListDatabase.watchListDao()
    }
}