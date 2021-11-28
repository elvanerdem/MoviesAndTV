package com.elvanerdem.moviesandtv.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elvanerdem.moviesandtv.data.Result

/** 
 * Created by elvanerdem 18.10.2021 
 */
abstract class BaseViewModel: ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
    get() = _isLoading

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _hasError = MutableLiveData(false)
    val hasError: LiveData<Boolean>
        get() = _hasError

    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    private fun setErrorMessage(message: String) {
        _errorMessage.value = message
    }

    open fun setResponseData(response: Result<Any>) {
        when(response) {
            is Result.Loading -> {
                setLoading(true)
            }
            is Result.Error -> {
                _hasError.value = true
                setLoading(false)
                setErrorMessage(response.message)
            }
            else -> {
                setLoading(false)
            }
        }
    }

}