package com.template.androidtemplateproject.data.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain:Interceptor.Chain): Response {
        var req=chain.request()
        // DONT INCLUDE API KEYS IN YOUR SOURCE CODE
        val url= req.url.newBuilder().addQueryParameter("APPID","your_key_here").build()
        req=req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}