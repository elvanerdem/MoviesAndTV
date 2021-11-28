package com.elvanerdem.moviesandtv.data.remote

import com.elvanerdem.moviesandtv.BuildConfig
import com.elvanerdem.moviesandtv.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

/**
 * Created by elvanerdem on 28.02.2021.
 */
@Singleton
class RequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val httpUrl = request.url.newBuilder()
            .addQueryParameter(Constants.PARAM_API_KEY, BuildConfig.API_KEY)
            .addQueryParameter(Constants.PARAM_LANGUAGE, Constants.LANGUAGE)
            .build()
        val requestBuilder = request.newBuilder().url(httpUrl)

        return chain.proceed(requestBuilder.build())
    }
}