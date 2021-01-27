package com.template.androidtemplateproject.data.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        // ADICIONE A CHAVE DA SUA API AQUI
        val url =
            req.url.newBuilder().addQueryParameter("", "")
                .build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}