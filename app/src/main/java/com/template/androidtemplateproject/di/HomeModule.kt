package com.template.androidtemplateproject.di

import com.template.androidtemplateproject.data.repository.HomeRepository
import com.template.androidtemplateproject.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    // Pode ser usado um 'single' pra definição de um factory geral
    factory { HomeRepository() }

    viewModel { HomeViewModel(get()) }
}