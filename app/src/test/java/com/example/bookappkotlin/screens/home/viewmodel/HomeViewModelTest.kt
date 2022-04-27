package com.example.bookappkotlin.screens.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bookappkotlin.repositories.network.api.model.Meme
import com.example.bookappkotlin.repositories.network.api.model.MemeResponse
import com.example.bookappkotlin.repositories.network.api.model.MemeValue
import com.example.bookappkotlin.screens.home.services.HomeServices
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class HomeViewModelTest : KoinTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val viewModel: HomeViewModel by inject()

    @Mock
    lateinit var mockedHomeServices : HomeServices

    private var expectedListOfMemes =  listOf(
        Meme(
            boxCount = 2,
            height = 1200,
            id = "181913649",
            name = "Drake Hotline Bling",
            url = "https://i.imgflip.com/30b1gx.jpg",
            width = 1200
        ), Meme(
            boxCount = 3,
            height = 908,
            id = "87743020",
            name = "Two Buttons",
            url = "https://i.imgflip.com/1g8my4.jpg",
            width = 600
        ), Meme(
            boxCount = 3,
            height = 800,
            id = "112126428",
            name = "Distracted Boyfriend",
            url = "https://i.imgflip.com/1ur9b0.jpg",
            width = 1200
        )
    )

    private var mockedHomeServicesGetAllMemes: Observable<MemeResponse?> = Observable.create {
        it.onNext(
            MemeResponse(
                success = true,
                data = MemeValue(
                    meme = listOf(
                        Meme(
                            boxCount = 2,
                            height = 1200,
                            id = "181913649",
                            name = "Drake Hotline Bling",
                            url = "https://i.imgflip.com/30b1gx.jpg",
                            width = 1200
                        ), Meme(
                            boxCount = 3,
                            height = 908,
                            id = "87743020",
                            name = "Two Buttons",
                            url = "https://i.imgflip.com/1g8my4.jpg",
                            width = 600
                        ), Meme(
                            boxCount = 3,
                            height = 800,
                            id = "112126428",
                            name = "Distracted Boyfriend",
                            url = "https://i.imgflip.com/1ur9b0.jpg",
                            width = 1200
                        )
                    )
                )
            )
        )
    }

    @Before
    fun beforeIt() {
        MockitoAnnotations.openMocks(this)
        startKoin {
            modules(
                module {
                    single<HomeServices>{
                        mockedHomeServices
                    }

                    single<HomeViewModel> {
                        HomeViewModel()
                    }
                }
            )
        }
    }

    @Test
    fun `HomeViewModel - should insert the correct value in livedata during getAllData`() {

        `when`(mockedHomeServices.getAllMemes()).thenReturn(mockedHomeServicesGetAllMemes)

        viewModel.getAllMemes()

        viewModel.listMemeResponseLiveData.observeForever{ result ->
            assert(result == expectedListOfMemes)
        }
    }

    @Test
    fun `HomeViewModel - should call services correctly during signOut`(){

        viewModel.signOutUser()

        verify(mockedHomeServices, times(1)).signOutUser()
    }

    @After
    fun afterIt() {
        stopKoin()
    }
}