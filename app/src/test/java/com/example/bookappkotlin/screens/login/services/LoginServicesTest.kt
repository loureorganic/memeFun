package com.example.bookappkotlin.screens.login.services

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bookappkotlin.screens.login.model.UserLogin
import com.example.bookappkotlin.screens.login.repository.LoginRepository
import com.example.bookappkotlin.screens.login.utils.LoginConstants
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

class LoginServicesTest : KoinTest {

    private val services: UserLoginServices by inject()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockRepository : LoginRepository

    @Before
    fun beforeIt() {
        MockitoAnnotations.openMocks(this)
        startKoin {
            modules(
                module {
                    single<LoginRepository>{
                        mockRepository
                    }
                    single<UserLoginServices> {
                        UserLoginServices()
                    }
                }
            )
        }
    }

    @Test
    fun `UserLoginServices - Should return a string valid during dataValidation`() {

        val user = UserLogin(
            email = "kaiqueguimaraes@gmail.com",
            password = "123456"
        )

        val result = services.dataValidation(user)
        assert(result === LoginConstants.VALID)
    }

    @Test
    fun `UserLoginServices - Should verify if the repository has been called during loginUser`(){

        val user = UserLogin(
            email = "kaiqueguimaraes@gmail.com",
            password = "123456"
        )

        services.loginUser(user)

        verify(mockRepository, times(1)).loginUser(user)
    }

    @After
    fun afterIt() {
        stopKoin()
    }
}