package com.app.recipe.network.api

import com.app.recipe.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val httpUrl: HttpUrl = original.url
        val url: HttpUrl = httpUrl.newBuilder()
            .addQueryParameter("apiKey", BuildConfig.API_KEY)
            .build()
        val request: Request = original.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}