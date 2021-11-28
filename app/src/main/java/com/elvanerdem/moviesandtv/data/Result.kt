package com.elvanerdem.moviesandtv.data

/**  
 * Created by elvanerdem 17.10.2021 
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T): Result<T>()
    data class Loading(val isVisible: Boolean): Result<Nothing>()
    data class Error(val message: String): Result<Nothing>()
}
