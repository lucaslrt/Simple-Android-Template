package com.template.androidtemplateproject.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.template.androidtemplateproject.data.model.Content
import com.template.androidtemplateproject.data.repository.HomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    private val _contentList = MutableLiveData<ArrayList<Content>>()
    val contentList: LiveData<ArrayList<Content>> = _contentList

    fun getContent() {
        /*// MÉTODO SEM COROUTINE
        repository.getContentFromApi { contentList ->
            _contentList.postValue(contentList)
        }*/

        // MÉTODO COM COROUTINE
        CoroutineScope(Dispatchers.IO).launch {
            val contentList = withContext(Dispatchers.IO) {
                repository.getContentFromApi()
            }
            _contentList.postValue(contentList)
        }
    }

    // USAR QUANDO NÃO ESTIVER USANDO INJEÇÃO DE DEPENDENCIA
    /*@Suppress("UNCHECKED_CAST")
    class HomeViewModelFactory(private val repository: HomeRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(repository) as T
        }
    }*/
}