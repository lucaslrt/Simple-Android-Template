package com.template.androidtemplateproject.di

import com.template.androidtemplateproject.data.repository.WeatherRepository
import com.template.androidtemplateproject.ui.weather.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherModule = module {
    // Pode ser usado um 'single' pra definição de um factory geral
    factory { WeatherRepository(get(), get()) }
    viewModel { WeatherViewModel(get()) }
}