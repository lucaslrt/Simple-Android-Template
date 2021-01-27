package com.template.androidtemplateproject.ui.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.template.androidtemplateproject.data.api.ApiResource
import com.template.androidtemplateproject.data.model.TempData
import com.template.androidtemplateproject.data.model.Weather
import com.template.androidtemplateproject.data.repository.WeatherRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class WeatherViewModelTest : KoinTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Mock
    private lateinit var weatherResponseLiveDataObserver: Observer<ApiResource<Weather>>

    private val viewModel: WeatherViewModel by inject()

    @Before
    fun setUp() {
        startKoin {
            modules(
                module {
                    single { WeatherRepository(get(), get()) }
                    single { WeatherViewModel(get()) }
                })
        }

        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `check if content live data receives a weather response when called method 'getWeather'`() {
        // Arrange
        val mockContent =
            ApiResource.success(Weather(TempData(1.0, 1), "Test Weather"))
        runBlocking {
            declareMock<WeatherRepository> {
                given(this.getForecast()).willReturn(
                    mockContent
                )
            }
        }
        viewModel.weatherResponse.observeForever(weatherResponseLiveDataObserver)

        // Act
        viewModel.getWeather()

        // Assert
        verify(weatherResponseLiveDataObserver).onChanged(mockContent)
    }
}