package com.elvanerdem.moviesandtv.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elvanerdem.moviesandtv.data.remote.api.MovieApi
import com.elvanerdem.moviesandtv.model.movie.Movie
import com.elvanerdem.moviesandtv.utils.Constants
import javax.inject.Inject

/** 
 * Created by elvanerdem 19.10.2021 
 */
class MoviesPagingSource @Inject constructor(private val movieApi: MovieApi): PagingSource<Int,Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageNumber = params.key ?: 1
        return try {
            val response = movieApi.getPopularMoviesForFlow(pageNumber)
            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (response.results.isEmpty()) null else pageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int = 1

}