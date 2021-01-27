package com.template.androidtemplateproject.util

import androidx.test.espresso.idling.CountingIdlingResource
import com.template.androidtemplateproject.BuildConfig

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    @JvmField val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment(){
        if(!BuildConfig.DEBUG) return
        countingIdlingResource.increment()
    }

    fun decrement(){
        if(!BuildConfig.DEBUG) return
        if(!countingIdlingResource.isIdleNow)
            countingIdlingResource.decrement()
    }
}