package com.template.androidtemplateproject.data.repository

import com.template.androidtemplateproject.data.model.Content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.security.auth.callback.Callback

class HomeRepository {

    // EXEMPLO PARA CHAMADA DE API SEM UTILIZAR COROUTINES
    fun getContentFromApi(callback: (contentList: ArrayList<Content>) -> Unit) {
        Thread(Runnable {
            Thread.sleep(3000)
            callback.invoke(
                arrayListOf(Content(1, "Dado 1"), Content(2, "Dado 2"))
            )
        }).start()
    }

    // EXEMPLO UTILIZANDO COROUTINES
    suspend fun getContentFromApi(): ArrayList<Content>{
        return withContext(Dispatchers.IO){
            delay(3000)
            arrayListOf(Content(1, "Dado 1"), Content(2, "Dado 2"))
        }
    }
}