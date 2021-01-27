package com.template.androidtemplateproject.di

import com.template.androidtemplateproject.BuildConfig
import com.template.androidtemplateproject.data.api.AuthInterceptor
import com.template.androidtemplateproject.data.api.DataApi
import com.template.androidtemplateproject.data.api.ResponseHandler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideApi(get()) }
    single { ResponseHandler() }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).addInterceptor(interceptor)
        .build()
}

fun provideApi(retrofit: Retrofit): DataApi = retrofit.create(DataApi::class.java)