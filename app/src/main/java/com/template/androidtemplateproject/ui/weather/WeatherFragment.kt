package com.template.androidtemplateproject.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.template.androidtemplateproject.MainActivity
import com.template.androidtemplateproject.R
import com.template.androidtemplateproject.data.api.ApiResource
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by viewModel()
    private lateinit var tvWeatherInfo: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_weather, container, false)
        tvWeatherInfo = root.findViewById(R.id.text_dashboard)

        initObservers()
        weatherViewModel.getWeather()
        return root
    }

    private fun initObservers() {
        weatherViewModel.weatherResponse.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                ApiResource.Companion.ApiStatus.LOADING -> (activity as MainActivity).showLoading()
                ApiResource.Companion.ApiStatus.SUCCESS -> {
                    (activity as MainActivity).hideLoading()
                    if (result.data != null) {
                        tvWeatherInfo.text = "Temperature at ${result.data.name} is ${result.data.temp.temp} celcius."
                    }
                }
                else -> {
                    (activity as MainActivity).hideLoading()
                    (activity as MainActivity).showErrorScreen()
                }
            }
        })
    }
}