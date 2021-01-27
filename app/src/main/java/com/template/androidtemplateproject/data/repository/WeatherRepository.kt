package com.template.androidtemplateproject.data.repository

import com.template.androidtemplateproject.data.api.ApiResource
import com.template.androidtemplateproject.data.api.DataApi
import com.template.androidtemplateproject.data.api.ResponseHandler
import com.template.androidtemplateproject.data.model.Weather
import com.template.androidtemplateproject.util.EspressoIdlingResource

class WeatherRepository(
    private val dataApi: DataApi,
    private val responseHandler: ResponseHandler
) {
    suspend fun getForecast(): ApiResource<Weather> {
        EspressoIdlingResource.increment()
        return try {
            responseHandler.handleSuccess(dataApi.getForecast())
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}