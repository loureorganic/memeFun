package com.example.bookappkotlin.screens.login.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bookappkotlin.screens.login.model.UserLogin
import com.example.bookappkotlin.screens.login.services.LoginService
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
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

class LoginViewModelTest : KoinTest {

    private val viewModel: LoginViewModel by inject()

    @Mock
    lateinit var mockLoginServices: LoginService

    private var mockedLoginUserLoginServices: Observable<Boolean> = Observable.create { it -> it.onNext(true) }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun beforeIt() {
        MockitoAnnotations.openMocks(this)
        startKoin {
            modules(
                module {
                    single<LoginService> {
                        mockLoginServices
                    }
                    single<LoginViewModel> {
                        LoginViewModel()
                    }
                }
            )
        }
    }

    @After
    fun afterIt() {
        stopKoin()
    }

    @Test
    fun `LoginViewModel - should return valid during dataValidation`() {

        val user = UserLogin(
            email = "kaique@gmail.com",
            password = "1612091"
        )

        viewModel.dataValidation(user)

        Mockito.verify(mockLoginServices, times(1)).dataValidation(user)
    }

    @Test
    fun `LoginViewModel - should fill the livedata values with Services Observables during loginUser`() {
        val user = UserLogin(
            email = "kaiqueguimaraes@gmail.com",
            password = "123456"
        )

        `when`(mockLoginServices.loginUser(user)).thenReturn(mockedLoginUserLoginServices)

        viewModel.loginUser(user)

        viewModel.booleanLoginAccountLiveData.observeForever { result ->
            assert(result == true)
        }

        viewModel.errorLoginAccountLiveData.observeForever {  result ->
            assert(result == false)
        }
    }
}
