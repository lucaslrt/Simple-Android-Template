package com.template.androidtemplateproject.data.api

import com.template.androidtemplateproject.data.model.Weather
import retrofit2.http.GET

interface DataApi {
    @GET("weather?q=Helsinki&units=metric")
    suspend fun getForecast(): Weather
}