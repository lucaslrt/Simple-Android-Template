package com.template.androidtemplateproject.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.template.androidtemplateproject.data.ApiResource
import com.template.androidtemplateproject.data.model.Content
import com.template.androidtemplateproject.data.repository.HomeRepository
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

class HomeViewModelTest : KoinTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Mock
    private lateinit var contentListLiveDataObserver: Observer<ApiResource<ArrayList<Content>>>

    private val viewModel: HomeViewModel by inject()

    @Before
    fun setUp() {
        startKoin {
            modules(
                module {
                    single { HomeRepository() }
                    single { HomeViewModel(get()) }
                })
        }

        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `check if content live data receives a list of contents when called method 'getContent'`() {
        // Arrange
        val mockContent = ApiResource.success(arrayListOf(Content(1, "Data 1"), Content(2, "Data 2")))
        runBlocking {
            declareMock<HomeRepository> {
                given(this.getContentFromApi()).willReturn(
                    mockContent
                )
            }
        }
        viewModel.contentList.observeForever(contentListLiveDataObserver)

        // Act
        viewModel.getContent()

        // Assert
        verify(contentListLiveDataObserver).onChanged(mockContent)
    }
}