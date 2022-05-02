package com.example.bookappkotlin.screens.home.services

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bookappkotlin.screens.home.repository.HomeRepository
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
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class HomeServicesTest : KoinTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockedRepository: HomeRepository

    /*private val expectedRepositoryResponse = Observable<Response<MemeResponse>> {
        it.onNext(
            MemeResponse(
                success = true,
                data = MemeValue(
                    listOf(
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

            ))
    }*/

    private val services: UserHomeServices by inject()

    @Before
    fun beforeIt() {
        MockitoAnnotations.openMocks(this)
        startKoin {
            modules(
                module {
                    single<HomeRepository> {
                        mockedRepository
                    }
                    single<UserHomeServices> {
                        UserHomeServices()
                    }
                }
            )
        }
    }

    //Pending Retrofit tests

    @Test
    fun `HomeServices - should return a observable of memes during signOut`() {

        mockedRepository.signOutUser()

        verify(mockedRepository, times(1)).signOutUser()
    }

    @After
    fun afterIt() {
        stopKoin()
    }
}
