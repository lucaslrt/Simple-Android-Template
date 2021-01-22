package com.template.androidtemplateproject.data.repository

import com.template.androidtemplateproject.data.ApiResource
import com.template.androidtemplateproject.data.model.Content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class HomeRepository{

    // EXEMPLO PARA CHAMADA DE API SEM UTILIZAR COROUTINES
    /*fun getContentFromApi(callback: (contentList: ArrayList<Content>) -> Unit) {
        Thread(Runnable {
            Thread.sleep(3000)
            callback.invoke(
                arrayListOf(Content(1, "Dado 1"), Content(2, "Dado 2"))
            )
        }).start()
    }*/

    // EXEMPLO UTILIZANDO COROUTINES
    suspend fun getContentFromApi(): ApiResource<ArrayList<Content>> {
        return withContext(Dispatchers.IO) {
            delay(3000)
            ApiResource.success(arrayListOf(Content(1, "Dado 1"), Content(2, "Dado 2")))
        }
    }
}