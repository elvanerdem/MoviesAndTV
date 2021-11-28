package com.elvanerdem.moviesandtv.di

import com.elvanerdem.moviesandtv.BuildConfig
import com.elvanerdem.moviesandtv.data.remote.RequestInterceptor
import com.elvanerdem.moviesandtv.data.remote.api.MovieApi
import com.elvanerdem.moviesandtv.data.remote.api.SearchApi
import com.elvanerdem.moviesandtv.data.remote.api.TVSeriesApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by elvanerdem on 7.02.2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()


    @Provides
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient,  BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()

    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    fun provideTVSeriesApi(retrofit: Retrofit): TVSeriesApi {
        return retrofit.create(TVSeriesApi::class.java)
    }

    @Provides
    fun provideSearchApi(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }
}