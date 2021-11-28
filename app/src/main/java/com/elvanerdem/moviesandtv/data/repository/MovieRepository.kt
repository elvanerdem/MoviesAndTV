package com.elvanerdem.moviesandtv.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.elvanerdem.moviesandtv.data.Result
import com.elvanerdem.moviesandtv.data.remote.api.MovieApi
import com.elvanerdem.moviesandtv.data.remote.paging.MoviesPagingSource
import com.elvanerdem.moviesandtv.model.movie.Movie
import com.elvanerdem.moviesandtv.model.movie.MovieDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by elvanerdem on 7.02.2021.
 */
class MovieRepository @Inject constructor(private val movieApi: MovieApi) {

    fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { MoviesPagingSource(movieApi) }
        ).flow
    }

    fun getMovieDetail(movieId: Long): Flow<Result<MovieDetailResponse>> = flow {
        try {
            val response = movieApi.getMovieDetail(movieId)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Something went wrong"))
        }
    }

}