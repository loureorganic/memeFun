package com.example.bookappkotlin.screens.login.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bookappkotlin.repositories.helpper.AuthenticationHelper
import com.example.bookappkotlin.screens.login.model.UserLogin
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
import kotlin.test.assertNotNull

class LoginRepositoryTest : KoinTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository: UserLoginRepository by inject()

    @Mock
    lateinit var mockDatabaseAuth: AuthenticationHelper

    @Before
    fun beforeIt() {
        MockitoAnnotations.openMocks(this)
        startKoin {
            modules(
                module {
                    single<AuthenticationHelper> {
                        mockDatabaseAuth
                    }
                    single<UserLoginRepository> {
                        UserLoginRepository()
                    }
                }
            )
        }
    }

    @Test
    fun `UserLoginRepository - should return a observable during loginUser`() {

        val user = UserLogin(
            email = "kaique@gmail.com",
            password = "123456"
        )

        repository.loginUser(user)

        verify(mockDatabaseAuth, times(1)).loginUser(user)

        val result = repository.databaseAuthenticationHelper

        assertNotNull(result)
    }

    @After
    fun afterIt() {
        stopKoin()
    }
}