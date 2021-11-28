package com.elvanerdem.moviesandtv.data.remote.api

import com.elvanerdem.moviesandtv.model.base.BaseResponse
import com.elvanerdem.moviesandtv.model.movie.Movie
import com.elvanerdem.moviesandtv.model.movie.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by elvanerdem on 7.02.2021.
 */
interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMoviesForFlow(@Query("page") page: Int): BaseResponse<Movie>

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(@Path("movieId") movieId: Long): MovieDetailResponse

}