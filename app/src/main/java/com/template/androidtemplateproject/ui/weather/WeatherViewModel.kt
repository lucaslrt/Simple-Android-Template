package com.template.androidtemplateproject.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.template.androidtemplateproject.data.api.ApiResource
import com.template.androidtemplateproject.data.model.Content
import com.template.androidtemplateproject.data.model.Weather
import com.template.androidtemplateproject.data.repository.WeatherRepository
import com.template.androidtemplateproject.util.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _weatherResponse = MutableLiveData<ApiResource<Weather>>()
    val weatherResponse: LiveData<ApiResource<Weather>> = _weatherResponse

    fun getWeather(){
        _weatherResponse.postValue(ApiResource.loading(null))
        CoroutineScope(Dispatchers.IO).launch {
            val contentList = withContext(Dispatchers.IO) {
                repository.getForecast()
            }
            _weatherResponse.postValue(contentList)
            EspressoIdlingResource.decrement()
        }
    }
}